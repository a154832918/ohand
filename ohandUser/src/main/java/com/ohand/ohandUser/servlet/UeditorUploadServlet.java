//package com.ohand.ohandUser.servlet;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
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
//import com.ohand.common.json.JsonUtil;
//import com.ohand.ohand.service.AttachFileService;
//import com.ohand.word.Word2Html;
//
//public class UeditorUploadServlet extends HttpServlet {
//	
//	
//	public static final String RESPONCE_TYPE_TEXT = "text";
//	
//	public static final String RESPONCE_TYPE_HTML = "html";
//	
//	public static final String RESPONCE_TYPE_JSON = "json";
//	
//	public static final String RESPONCE_TYPE_XML = "xml";
//	
//	private static final long serialVersionUID = 630271418617625412L;
//	
//	@Resource
//	private transient AttachFileService attachFileService;
//	
//	public AttachFileService getAttachFileService() {
//		return attachFileService;
//	}
//
//	public void setAttachFileService(AttachFileService attachFileService) {
//		this.attachFileService = attachFileService;
//	}
//
//	public UeditorUploadServlet() {
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
//			try {  
//			            DiskFileItemFactory fac = new DiskFileItemFactory();  
//			            ServletFileUpload upload = new ServletFileUpload(fac);  
//			            upload.setHeaderEncoding("UTF-8");  
//			            List fileList = upload.parseRequest(request);  
//			            Iterator it = fileList.iterator();
//			            while (it.hasNext()) {  
//			            	Object obit = it.next();
//			            	if(obit instanceof DiskFileItem){
//				                DiskFileItem item = (DiskFileItem) obit;  
//				                String fileName = item.getName();  
//				                if (fileName != null) {  
//				                	String fileId=UUID.randomUUID().toString()+".doc";
//				                    String savePath=PFConstant.personImageFile+File.separator+ fileId;
//				                      File expsfile=new File(PFConstant.personImageFile);
//				                      if(!expsfile.exists()){
//				                    	  expsfile.mkdirs();
//				                      }
//				                    BufferedInputStream in = new BufferedInputStream(item.getInputStream());
//				                    BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
//				                    Streams.copy(in, outStream, true);
//				                    if (new File(savePath).exists()) {
//				                    	String htmlPath=Word2Html.wordToHtml(PFConstant.personImageFile, File.separator+ fileId);
//				                    	System.out.println("htmlPath-----------------------------------------："+htmlPath);
//				                    }
//				                  }  
//				                }   
//			            	}
//			            
//			            initJsonResponse(response, JsonUtil.obj2Json("true"),RESPONCE_TYPE_JSON);
//			            
//			        } catch (org.apache.commons.fileupload.FileUploadException ex) {
//			    	   ex.printStackTrace();  
//			           return;  
//					}
//		}catch(Exception ex){
//			ex.printStackTrace();
//			throw new BussinessException("上传附件失败"+ex);
//		}
//	}
//	
//	
//	public void initJsonResponse(HttpServletResponse response, String json,String responseType)
//			throws IOException {
//	    String contentType = null;
//        if (RESPONCE_TYPE_HTML.equalsIgnoreCase(responseType)) {
//            contentType = "text/html;charset=utf-8";
//        } else if (RESPONCE_TYPE_JSON.equalsIgnoreCase(responseType)) {
//            contentType = "text/json;charset=utf-8";
//        } else if (RESPONCE_TYPE_TEXT.equalsIgnoreCase(responseType)) {
//            contentType = "text/plain;charset=utf-8";
//        } else if (RESPONCE_TYPE_XML.equalsIgnoreCase(responseType)) {
//            contentType = "text/xml;charset=utf-8";
//        } else {
//            contentType = "text/html;charset=utf-8";
//        }
//        response.setHeader("Cache-Control", "no-cache");
//        response.setContentType(contentType);
//        PrintWriter out = null;
//        try {
//            out = response.getWriter();
//            out.write(json);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//        }
//	}
//}
