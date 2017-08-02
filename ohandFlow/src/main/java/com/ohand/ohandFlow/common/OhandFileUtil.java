package com.ohand.ohandFlow.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class OhandFileUtil {
	
	static File baiduDestFile = new File("ohand");
	public static Properties p=PropertyUtils.loadProperties("app.properties");
	public static final String OHAND_FILE_PATH=p.getProperty("ohanddoc")==null?"":(p.getProperty("ohanddoc").equals("ohanddoc")?"/ohandfile":p.getProperty("ohanddoc"));
	
	/**
	 *对百度BCS云与电脑文件路径的适配 
	 */
	public static String  getPathWrapper(String path){
		if(OHAND_FILE_PATH!=null&&OHAND_FILE_PATH.equals("/ohandfile")){
			path = path.replaceAll("\\\\", "/");  
		}
		return path;
	}
	/**
	 * 对百度BCS云与电脑文件保存操作的适配 
	 * @throws IOException 
	 */
	public static File saveFileWrapper(String path,String content,String ext){
		if(OHAND_FILE_PATH!=null&&OHAND_FILE_PATH.equals("/ohandfile")){
			String tempPath=new String();
			tempPath=getPathWrapper(path);
//			BosClient bosClient=BCEUtil.getBosClient();
//			BCEUtil.putObjectByFile(bosClient,tempPath, content,ext);
			return null;
		}else{
			File file = new File(path);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.err.println("ohand:create file fail!");
					e.printStackTrace();
				}
				file = new File(path);
			}
			try {
				FileUtils.writeStringToFile(file, content, "utf-8");
				return file;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 对百度BCS云与电脑文件读取操作的适配 
	 * @throws IOException 
	 */
	public static String readFileWrapper(String filepath,boolean needbr) throws IOException {
		if(OHAND_FILE_PATH!=null&&OHAND_FILE_PATH.equals("/ohandfile")){
			filepath=getPathWrapper(filepath);
			File destFile = new File(filepath);
//			BosClient bosClient=BCEUtil.getBosClient();
//			File file=BCEUtil.getObjectWithDestFile(bosClient,filepath);
//			if (!file.exists())
//				return "";
			String fileEncode=EncodingDetect.getJavaEncode(filepath);
			return FileUtils.readFileToString(destFile,fileEncode);
		}else{
			File f = new File(filepath); 
			if (!f.exists())
				return "";
			String fileEncode=EncodingDetect.getJavaEncode(filepath);
			return FileUtils.readFileToString(f,fileEncode);
		}
	}

	public static String getMappingFile() {
		String path = OHAND_FILE_PATH;
		String domainFileName = path + File.separator ;
		domainFileName=getPathWrapper(domainFileName);
		File file = new File(domainFileName);
		if (file.exists())
			return domainFileName;
		else
			return OHAND_FILE_PATH ;
	}

	public static long getLastModifyTime(String fileName) {
		fileName=getPathWrapper(fileName);
		File file = new File(fileName);
		long modifiedTime = file.lastModified();
		return modifiedTime;
	}

	public static boolean isExistsFile(String fileName) {
		fileName=getPathWrapper(fileName);
		File file = new File(fileName);
		return file.exists();
	}	

	public static String getPrintTempleteFile(String webPath, String dir,
			String id, String ext) {

		if (webPath.lastIndexOf(File.separator) != webPath.length() - 1)
			webPath = webPath + File.separator;
		String path = webPath + "ocx" + File.separator + dir;
		String file = id + "." + ext;
		String fileName = path + File.separator + file;

		if (isExistsFile(fileName))
			return getPathWrapper(fileName);
		else
			return null;
	}

	public static String extractFileName(String path) {
		String fName = path.trim();
		String temp[] = fName.split("\\" + File.separator);
		String fileName = temp[temp.length - 1];
		return fileName;
	}

	public static String getFileExt(String fileName) {
		String value = new String();
		int start = 0;
		int end = 0;
		if (fileName == null)
			return null;
		start = fileName.lastIndexOf('.') + 1;
		end = fileName.length();
		value = fileName.substring(start, end);
		if (fileName.lastIndexOf('.') > 0)
			return value;
		else
			return "";
	}

	//流程脚本文件复制
	public static void copyFlowDesignFile(String flowCode,String oldUuid,String newUuid) throws IOException {
	  // 因为我在数据里面定义的uuid类型为char(128)
	  oldUuid=oldUuid.trim();
	  newUuid=newUuid.trim();
	  String newPath = OHAND_FILE_PATH + File.separator+ "flowfile"+ File.separator + flowCode+ File.separator + newUuid;
	  String oldPath = OHAND_FILE_PATH + File.separator+ "flowfile"+ File.separator + flowCode+ File.separator + oldUuid;
	  
	  newPath=getPathWrapper(newPath);
	  oldPath=getPathWrapper(oldPath);
	  
	  System.out.println("=====oldPath==========="+oldPath);
	  
      //创建目标文件夹
      (new File(newPath)).mkdirs();
      //获取源文件夹当前下的文件或目录
       File[] file=(new File(oldPath)).listFiles();
       if(file!=null){
           for (int i = 0; i < file.length; i++) {
   	        if(file[i].isFile()){
   	            //复制文件
   	            copyFile(file[i],new File(newPath+File.separator+file[i].getName()));
   	        }
   	        if(file[i].isDirectory()){
   	            //复制目录
   	            String sorceDir=oldPath+File.separator+file[i].getName();
   	            String targetDir=newPath+File.separator+file[i].getName();
   	            copyDirectiory(sorceDir, targetDir);
   	        }
          }    	   
       }
	}
	/**
	 * 保存表单模板
	 * @param templeteId
	 * @param formId
	 * @param xml
	 * @return
	 */
	public static String saveFormDesignFile(String templeteId,String formId, String xml) {
		String path = OHAND_FILE_PATH + File.separator + "formfile"
				+ File.separator + templeteId;
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
//		String fileName="F:\\myComputer\\learnCodeWorkSpace\\android\\ionic\\myAppIonic01\\platforms\\android\\assets\\www\\design\\"+ File.separator + formId + ".html";
		String fileName = path + File.separator + formId + ".html";
		saveFileWrapper(fileName, xml, ".html");
		return getPathWrapper(fileName);
	}

	/**
	 * 保存Portlet模板
	 * @param templeteId：表单code
	 * @param portletUuid ：portlet的Uuid
	 * @param xml
	 * @return
	 */
	public static String savePortletDesignFile(String templeteId,String formId,String portletUuid, String xml) {
		String path = OHAND_FILE_PATH + File.separator + "formfile"
				+ File.separator + templeteId+ File.separator + formId;
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		String fileName = path + File.separator + portletUuid + ".html";
		saveFileWrapper(fileName, xml, ".html");
		return getPathWrapper(fileName);
	}
	
	/**
	 * 保存表单初始化脚本
	 * @param templeteId
	 * @param formId
	 * @param xml
	 * @return
	 */
	public static String saveFormInitScriptFile(String templeteId,String formId, String xml) {
		String path = OHAND_FILE_PATH + File.separator+ "formfile"+ File.separator + templeteId;
		String fileName = path + File.separator + formId + ".js";
		saveFileWrapper(fileName, xml, ".js");
		return getPathWrapper(fileName);
	}
	
	/**
	 * 读取整个form设计器html
	 * templeteId ：表单模板code
	 * formId ：表单id 
	 */
	public static String readFormDesignFile(String templeteId,String formId) throws IOException {

		String path = OHAND_FILE_PATH + File.separator+ "formfile"+ File.separator + templeteId;
		String fileName = path + File.separator + formId+".html";
		return readFile(fileName, false);
	}
	
	/**
	 * 读取单个portlet内容
	 * templeteId ：表单模板code
	 * portletUuid ：portlet的uuid 
	 */
	public static String readPortletDesignFile(String templeteId,String formId,String portletUuid) throws IOException {

		String path = OHAND_FILE_PATH + File.separator+ "formfile"+ File.separator + templeteId+ File.separator + formId;
		String fileName = path + File.separator + portletUuid+".html";
		String content=readFile(fileName, false);
		return content;
	}
	
	public static String readFormInitScriptFile(String templeteId,String formId) throws IOException {
		String path = OHAND_FILE_PATH + File.separator+ "formfile"+ File.separator + templeteId;
		String fileName = path + File.separator + formId+".js";
		return readFile(fileName, false);
	}
	
	public static String readFile(String filepath, boolean needbr) throws IOException {
		return readFileWrapper(filepath,needbr);
	}
	
	public static void saveActivityScriptFile(String flowCode,String uuid,String itemId, String flowData) {
		String path = OHAND_FILE_PATH + File.separator+ "flowfile"+ File.separator + flowCode+ File.separator + uuid;
		path=path+ File.separator + itemId + ".txt";
		saveFileWrapper(path,flowData,".txt");
	}
	
	public static String readScriptFile(String flowCode,String uuid,String itemId,boolean needbr) throws IOException {
		String path = OHAND_FILE_PATH + File.separator+ "flowfile";
		File base = new File(path);
		if (!base.exists())
			base.mkdirs();
		String filename = path + File.separator + flowCode+ File.separator + uuid+ File.separator + itemId+".txt";
		return readFile(filename, needbr);
	}

	public static long getFileSizes(String filePath) throws Exception {
		long s = 0;
		File f = new File(filePath);
		if (f.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				s = fis.available();
			} finally {
				if (fis != null)
					fis.close();
			}
		} else {
		}
		return s;
	}
	
	public static void copyFile(File sourcefile,File targetFile) throws IOException{
		FileUtils.copyFile(sourcefile, targetFile);
    }

	
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		if(file!=null){
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					// 源文件
					File sourceFile = file[i];
					// 目标文件
					File targetFile = new File(
							new File(targetDir).getAbsolutePath() + File.separator
									+ file[i].getName());

					copyFile(sourceFile, targetFile);
				}
				if (file[i].isDirectory()) {
					// 准备复制的源文件夹
					String dir1 = sourceDir + file[i].getName();
					// 准备复制的目标文件夹
					String dir2 = targetDir + File.separator + file[i].getName();
					copyDirectiory(dir1, dir2);
				}
			}
		}
	}
	
	//截取文件名
	public static String trimExtension(String filename) { 
		if ((filename != null) && (filename.length() > 0)) { 
			int i = filename.lastIndexOf('.'); 
			if ((i >-1) && (i < (filename.length()))) { 
				return filename.substring(0, i); 
			} 
		} 
		return filename; 
	}
	
	/**
	 * 加载ohand-->commonProperties下面的properties文件
	 * @param name
	 * @return
	 */
	public static String getCommonPropertiesServerPath(String name) {
		String path = OHAND_FILE_PATH;
		File base = new File(path);
		if (!base.exists())
			base.mkdir();
		if (name == null)
			return null;
		//读取文件
		if (name.indexOf(".") < 0)
			name = name + ".properties";
		String fileName = path + File.separator + name;
		File file = new File(fileName);
		if (file.exists())
			return fileName;
		else
			return PFConstant.JAR_MAPPING_PATH + name;
	}
	
	  public static InputStream getResourceAsStream(String name) {
		    InputStream resourceStream = null;
		    ClassLoader classLoader = null;//getCustomClassLoader();
		    
//		    if(classLoader != null) {
//		      resourceStream = classLoader.getResourceAsStream(name);
//		    }
		    
		    if(resourceStream == null) {
		      // Try the current Thread context classloader
		      classLoader = Thread.currentThread().getContextClassLoader();
		      resourceStream = classLoader.getResourceAsStream(name);
		      if(resourceStream == null) {
		        // Finally, try the classloader for this class
		        classLoader = OhandFileUtil.class.getClassLoader();
		        resourceStream = classLoader.getResourceAsStream(name);
		      }
		    }
		    return resourceStream;
	}
	
  /**
   * Closes the given stream. The same as calling {@link InputStream#close()}, but
   * errors while closing are silently ignored.
   */
  public static void closeSilently(InputStream inputStream) {
	    try {
	      if(inputStream != null) {
	        inputStream.close();
	      }
	    } catch(IOException ignore) {
	      // Exception is silently ignored
	    }
  }  

  /**
   * Closes the given stream. The same as calling {@link OutputStream#close()}, but
   * errors while closing are silently ignored.
   */
  public static void closeSilently(OutputStream outputStream) {
    try {
      if(outputStream != null) {
        outputStream.close();
      }
    } catch(IOException ignore) {
    	
    }
  } 
  
}
