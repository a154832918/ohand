//package com.ohand.ohandUser.servlet;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.UUID;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.fileupload.disk.DiskFileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.fileupload.util.Streams;
//
//import com.ohand.common.common.PFConstant;
//import com.ohand.common.common.UserInfo;
//import com.ohand.common.exception.BussinessException;
//import com.ohand.common.file.OhandFileUtil;
//import com.ohand.ohand.domain.PersonImageDomain;
//import com.ohand.ohand.service.PersonImageService;
//
//public class PersonImageUploadServlet extends HttpServlet {
//
//	private static final long serialVersionUID = 1706553413097584307L;
//	
//	@Resource
//	private transient PersonImageService personImageService ;
//	
//	public PersonImageService getPersonImageService() {
//		return personImageService;
//	}
//
//	public void setPersonImageService(PersonImageService personImageService) {
//		this.personImageService = personImageService;
//	}
//
//	public PersonImageUploadServlet() {
//		super();
//	}
//	
//	public void destroy() {
//		super.destroy();
//	}
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	/**
//	 * 获取当前用户
//	 * 
//	 * @param request
//	 * @return 当时登录用户
//	 */
//	 protected UserInfo getCurrUserInfo(HttpServletRequest request) {
//		 Object obj=request.getSession().getAttribute(PFConstant.SESSION_USER_KEY);
//		 UserInfo currUser=obj==null?null:(UserInfo)obj;
//		return currUser;
//	 }
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		try{
//			UserInfo userInfo=getCurrUserInfo(request);
//			String relatedCommonId=request.getParameter("relatedCommonId");
//			String imageType=request.getParameter("imageType");
//			try {  
//			            DiskFileItemFactory fac = new DiskFileItemFactory();  
//			            ServletFileUpload upload = new ServletFileUpload(fac);  
//			            upload.setHeaderEncoding("UTF-8");  
//			            List fileList = upload.parseRequest(request);  
//			            Iterator it = fileList.iterator();
//			            String key=request.getParameter("id");
//			            while (it.hasNext()) {  
//			            	Object obit = it.next();
//			            	if(obit instanceof DiskFileItem){
//				                DiskFileItem item = (DiskFileItem) obit;  
//				                String fileName = item.getName();  
//				                if (fileName != null) {  
//				                    String fName=item.getName().substring(item.getName().lastIndexOf("\\")+1);  
//				                    String formatName = fName.substring(fName.lastIndexOf(".")+1);
//				                    String savePath=PFConstant.personImageFile+File.separator+ UUID.randomUUID().toString();
//				                      File expsfile=new File(PFConstant.personImageFile);
//				                      if(!expsfile.exists()){
//				                    	  expsfile.mkdirs();
//				                      }
//				                    BufferedInputStream in = new BufferedInputStream(item.getInputStream());
//				                    BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
//				                    Streams.copy(in, outStream, true);
//				                    if (new File(savePath).exists()) {
//				                    	if(key!=null){
//				                    		PersonImageDomain vo=personImageService.getObjectVOById((key));
//							            		vo.setFileName(OhandFileUtil.trimExtension(fName));
//							            		vo.setUploaderDate(new Date());
//							            		vo.setSuffix(formatName);
//							            		vo.setFilePath(savePath);
//							            		vo.setUploader(userInfo.getUserName());
//							            		vo.setUploaderId(userInfo.getCommonId());
//							            		vo.setRelatedCommonId(new Integer(relatedCommonId));
//							            		vo.setImageType(new Integer(imageType));
//							            		personImageService.updateObject(vo);
//				                    	}else{
//				                    		PersonImageDomain vo=new PersonImageDomain();
//							            		vo.setFileName(OhandFileUtil.trimExtension(fName));
//							            		vo.setUploaderDate(new Date());
//							            		vo.setSuffix(formatName);
//							            		vo.setFilePath(savePath);
//							            		vo.setUploader(userInfo.getUserName());
//							            		vo.setUploaderId(userInfo.getCommonId());
//							            		vo.setRelatedCommonId(new Integer(relatedCommonId==null?"0":relatedCommonId));
//							            		vo.setImageType(new Integer(imageType));
//							            		personImageService.insertObject(vo);
//				                    	}
//				                    }
//				                  }  
//				                }   
//			            	}
//			        } catch (org.apache.commons.fileupload.FileUploadException ex) {
//			    	   ex.printStackTrace();  
//			           return;  
//					}
//		}catch(Exception ex){
//			ex.printStackTrace();
//			throw new BussinessException("上传附件失败"+ex);
//		}
//	}
//}
