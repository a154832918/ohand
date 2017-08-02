package com.ohand.ohandFlow.script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.ohand.common.exception.BussinessException;

/**
 * @author liud
 *
 *  
 */
public class OhandScriptFileUtil {

	public static final String MAPPING_FILE = "mapping-config.xml";
    
	private static void init() {

		String path = OhandScriptConstants.CONVERT_FILE_PATH;
		File base = new File(path);
		if (!base.exists())
			base.mkdir();

	}
	/**
	 */
	public static String getMappingFile() {

		init();
		String path = OhandScriptConstants.CONVERT_FILE_PATH;
		String domainFileName = path + File.separator + MAPPING_FILE;
		File file = new File(domainFileName);
		if (file.exists())
			return domainFileName;
		else
			return OhandScriptConstants.JAR_MAPPING_PATH + MAPPING_FILE;

	}

	/**
	 * @param name
	 * @return
	 */
	public static String getPropertiesServerPath(String name) {

		if (name == null)
			return null;
		init();
		String path = OhandScriptConstants.CONVERT_FILE_PATH;
		if (name.indexOf(".") < 0)
			name = name + ".properties";
		String domainFileName = path + File.separator + name;

		File file = new File(domainFileName);
		if (file.exists())
			return domainFileName;
		else
			return OhandScriptConstants.JAR_MAPPING_PATH + name;
	}
	/**
	 * @param name
	 * @return
	 */
	public static String getConfigPropertiesServerPath(String name) {

		if (name == null)
			return null;
		init();
		String path = OhandScriptConstants.CONVERT_FILE_PATH;
		if (name.indexOf(".") < 0)
			name = name + ".properties";
		String domainFileName = path + File.separator+"conf"+File.separator + name;

		File file = new File(domainFileName);
		if (file.exists())
			return domainFileName;
		else
			return OhandScriptConstants.JAR_MAPPING_PATH + name;
	}
	/**
	 * @param name
	 * @return
	 */
	public static String getClientJsPropertiesServerPath(String name) {

		if (name == null)
			return null;
		init();
		String path = OhandScriptConstants.CLIENT_CONVERT_FILE_PATH;
		if (name.indexOf(".") < 0)
			name = name + ".properties";
		String domainFileName = path + File.separator + name;

		File file = new File(domainFileName);
		if (file.exists())
			return domainFileName;
		else
			return null;
	}

	public static long getLastModifyTime(String fileName) {

		File file = new File(fileName);
		long modifiedTime = file.lastModified();

		return modifiedTime;

	}

	public static boolean isExistsFile(String fileName) {

		File file = new File(fileName);
		return file.exists();

	}

	public static String getJdpjExportTempleteFile(String webPath) {

		if (webPath.lastIndexOf(File.separator) != webPath.length() - 1)
			webPath = webPath + File.separator;
		return webPath + "ocx" + File.separator + "jdpj_xqjh.xls";
	}
	public static String getJdpjExportFile(String webPath, String ids) {

		if (webPath.lastIndexOf(File.separator) != webPath.length() - 1)
			webPath = webPath + File.separator;
		String fileName = webPath + "temp" + File.separator + ids + ".xls";

		return fileName;

	}	
	
	public static boolean isExistsBpmTempleteFile(String webPath,String projectStage,String processStatus){
		
		if (webPath.lastIndexOf(File.separator) != webPath.length() - 1)
				webPath = webPath + File.separator;
			String path = webPath + "gctz" + File.separator + "bpm"+File.separator+"templete";
			String file = projectStage+"_"+processStatus+".jsp";
			String fileName = path+File.separator+file;
		
			return  isExistsFile(fileName);
	}
	
	public static String getBpmTempleteFile(String webPath,String projectStage,String processStatus) {

		if (webPath.lastIndexOf(File.separator) != webPath.length() - 1)
			webPath = webPath + File.separator;
		String path = webPath + "gctz" + File.separator + "bpm"+File.separator+"templete";
		String file = projectStage+"_"+processStatus+".jsp";
		String fileName = path+File.separator+file;
		
		if(isExistsFile(fileName)) return "/gctz/bpm/templete/"+file;
		else{
			file = "default.jsp";
			fileName= path+File.separator+file;
			if(isExistsFile(fileName)) return "/gctz/bpm/templete/"+file;
		}

		return "";

	}
	
	public static String getPrintTempleteFile(String webPath,String dir,String id,String ext){
		
		if (webPath.lastIndexOf(File.separator) != webPath.length() - 1)
			webPath = webPath + File.separator;
		String path = webPath + "ocx"+ File.separator+dir;
		String file = id+"."+ext;
		String fileName = path+File.separator+file;
		
		if(isExistsFile(fileName)) return fileName;
		else return null;
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

	public static String saveDesignFile(String templeteId, String xml) {

		String path = OhandScriptConstants.CONVERT_FILE_PATH + File.separator + "bizflow";
		File base = new File(path);
		if (!base.exists())
			base.mkdirs();

		FileWriter filewriter = null;
		BufferedWriter bufwriter = null;

		try {
			String fileName = path + File.separator + templeteId + ".xml";
			File file = new File(fileName);
			if (!file.exists()) {

				file.createNewFile();
				file = new File(fileName);

			}

			filewriter = new FileWriter(file);
			bufwriter = new BufferedWriter(filewriter);

			bufwriter.write(xml);
			bufwriter.flush();

			return fileName;

		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			if (bufwriter != null)
				try {
					bufwriter.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			if (filewriter != null)
				try {
					filewriter.close();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
		}

		return "";

	}
	public static String readDesignFile(String filename) {

		String path = OhandScriptConstants.CONVERT_FILE_PATH + File.separator + "bizflow";
		String fileName = path + File.separator + filename;

		return readFile(fileName, false);
	}
	public static String readFile(String filepath, boolean needbr) {

		FileReader fileread = null;
		BufferedReader bufread = null;
		try {
			String read = "";
			StringBuffer sb = new StringBuffer("");
			File file = new File(filepath);
			if (!file.exists())
				return "";
			fileread = new FileReader(file);
			bufread = new BufferedReader(fileread);
			while ((read = bufread.readLine()) != null) {
				sb.append(read);
				if (needbr)
					sb.append("\n\r");
			}

			return sb.toString();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			if (bufread != null)
				try {
					bufread.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			if (fileread != null)
				try {
					fileread.close();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
		}
		return "";
	}

	/**
	* @return
	*/
	public static String readScriptFile(
		String activityId,
		String type,
		boolean needbr) {

		String path = OhandScriptConstants.CONVERT_FILE_PATH + File.separator + "flowjs"+ File.separator+activityId;
		File base = new File(path);
		if (!base.exists())
			base.mkdirs();

		String filename = path + File.separator + activityId + type;

		return readFile(filename, needbr);

	}

	/**
	 */
	public static String saveScriptFile(
		String activityId,
		String type,
		String content) {
		String path = OhandScriptConstants.CONVERT_FILE_PATH + File.separator + "flowjs"+ File.separator+activityId;
		File base = new File(path);
		if (!base.exists())
			base.mkdirs();
		String filename = path + File.separator + activityId + type;
		FileWriter filewriter = null;
		BufferedWriter bufwriter = null;
		try {
			if(base.isDirectory()){
				if(base.listFiles().length > 0){
					File[] fileArr=base.listFiles();
					for(int i=0;i<fileArr.length;i++){
						File exchangeFile=null;
						for(int j=0;j<fileArr.length;j++){
							if(fileArr[i].lastModified()>fileArr[j].lastModified()){
								exchangeFile=fileArr[i];
								fileArr[i]=fileArr[j];
								fileArr[j]=exchangeFile;
							}
						}
					}
					boolean renameFileIs=fileArr[0].renameTo(new File(base+ File.separator+fileArr[0].getName()+"_v"+fileArr.length));
						if(renameFileIs!=true){
							throw new BussinessException();
					}	
				}
				
				File file = new File(filename);
				if (!file.exists()) {
					file.createNewFile();
					file = new File(filename);
				}
				filewriter = new FileWriter(file);
				bufwriter = new BufferedWriter(filewriter);
				bufwriter.write(content);
				bufwriter.flush();
				
			}
			return filename;

		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			if (bufwriter != null)
				try {
					bufwriter.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			if (filewriter != null)
				try {
					filewriter.close();
				} catch (IOException e2) {

					e2.printStackTrace();
				}
		}

		return null;

	}
	
	public static long getFileSizes(String filePath) throws Exception{
		long s=0; 
		
		File f = new File(filePath);
		
		if (f.exists()) { 
			FileInputStream fis = null; 
			try{
				
				fis = new FileInputStream(f); 
				s= fis.available(); 
			}finally{
				
				if(fis!=null) fis.close();
			}
			
		} else { 
			 
			System.out.println("------"); 
		} 
		return s; 
	} 

}
