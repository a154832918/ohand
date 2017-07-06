package com.ohand.spring01.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.ohand.spring01.common.JsonUtil;
import com.ohand.spring01.common.OhandFileUtil;
import com.ohand.spring01.common.UUIDUtil;
import com.ohand.spring01.domain.AttachFileDomain;
import com.ohand.spring01.domain.PersonDomain;
import com.ohand.spring01.framework.controller.BaseController;
import com.ohand.spring01.mongodb.repository.AttachFileRepository;
import com.ohand.spring01.mongodb1.GridFSFileUtil;
import com.ohand.spring01.service.AttachFileService;
import com.ohand.spring01.service.PersonService;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(HelloController.class);

	@Autowired  
	private PersonService personService; 
	
	@Autowired  
	private AttachFileService attachFileService; 	

	@Autowired
	private AttachFileRepository repository;
	
	@Autowired  
	private MongoDbFactory mongodbfactory; 
	
	@Autowired
	private DiscoveryClient client;
	
    @Autowired  
    private LoadBalancerClient loadBalancerClient;  
	
    @Autowired  
    private RestTemplate restTemplate; 
	
    @RequestMapping("/")
    public String index() {
    	 PersonDomain d=personService.getObjectVOById(399);
    	 ServiceInstance serviceInstance = this.loadBalancerClient.choose("ohandFLow"); 
    	 
    	 System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"  
                 + serviceInstance.getPort());// 打印当前调用服务的信息  

    	 String u=this.restTemplate.getForObject("http://ohandFLow/", String.class);
    	 
//    	 d.setCity(instance.getServiceId());
         return JsonUtil.obj2Json(u);
    }
    
    
    @RequestMapping("/mongodb")
    public String mongodb() {
    	repository.deleteAll();
    	AttachFileDomain a=new AttachFileDomain();
    	a.setUploaderDate(new Date());
    	a.setSuffix("xls");
//    	repository.save(a);
    	for(int i=10000;i<10010;i++){
    		a.setId("key_"+i);
    		a.setFileName("12313_"+i);
    		a.setFileName("---"+System.currentTimeMillis());
    		repository.insert(a);
    	}
    	
    	AttachFileDomain at=null;
		for (AttachFileDomain af : repository.findAll()) {
			System.out.println(af);
			at=af;
		}
        return JsonUtil.obj2Json(at);
    }
    
    // http://blog.csdn.net/wangjia55/article/details/51740437
    // 参考例子
    @RequestMapping("/upload")
    public String upload(HttpServletRequest request){
    
    	 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(  
    	            request.getSession().getServletContext());  
    	    // 先判断request中是否包涵multipart类型的数据，  
    	    String fileUrl = "";  
    	    if (multipartResolver.isMultipart(request)) {  
    	        // 再将request中的数据转化成multipart类型的数据  
    	        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
    	        Iterator<String> iter = multiRequest.getFileNames();  
	            String uploadNo=request.getParameter("uploadNo");
	    		if(uploadNo==null||uploadNo.equals("null")){
	    			//TODO  throw new Exception("没有传人参数存在问题...");
	    		}
    	        while (iter.hasNext()) {  
    	            MultipartFile file = multiRequest.getFile((String) iter.next());  
    	            if (file != null) { 
    	            	String fileUuid=UUIDUtil.generateUUID();
    	            	String fName=file.getOriginalFilename();
	                    String formatName = fName.substring(fName.lastIndexOf(".")+1);
	                    
    	                try {  
    	                    GridFSInputFile inputFile = GridFSFileUtil.saveMongoDBFile(mongodbfactory,"FILE_AttachFileDomain",file.getInputStream(), fileUuid,fName,file.getContentType(),fName);  
    	                    if (inputFile == null) {  
    	                        return "";//StatusConfig.FileUploadError;  
    	                    } else {  
    	                        fileUrl = inputFile.getId().toString();  
    	                    } 
    	                    
	                    	AttachFileDomain vo=new AttachFileDomain();
				            	vo.setFileName(OhandFileUtil.trimExtension(file.getName()));
				            	vo.setUploaderDate(new Date());
				            	vo.setSuffix(formatName);
//				            	vo.setUploader(userInfo.getUserName());
//				            	vo.setUploaderId(userInfo.getCommonId());
				            	vo.setUploadNo(uploadNo);
				            	vo.setFileUuid(fileUuid);
				            	vo.setCreateDate(new Date());
				            attachFileService.save(vo);
    	                    
    	                    
    	                } catch (IllegalStateException | IOException e) {  
    	                      e.printStackTrace();
    	                }  
    	            }  
    	        }  
    	     }
    	        return "";  
    }
    
	@RequestMapping(value = "mongodbFile/{id}", method = RequestMethod.GET)  
    public void getFile(@PathVariable String id, HttpServletResponse response) {  
    	GridFSDBFile file = GridFSFileUtil.readMongoDBFileById(mongodbfactory, (id),"FILE_AttachFileDomain");  
        OutputStream os = null;  
        try {  
            os = response.getOutputStream();  
            if(file!=null){
            	file.writeTo(os);
            }
            os.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
	
	/**
	 * 定时服务	
	 */
	@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        log.info("定时服务列表：执行：The time is now {}", dateFormat.format(new Date()));
    }

}