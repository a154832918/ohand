package com.ohand.ohandFlow.script;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;


public class ScriptClassLoader extends ClassLoader {
	
	private static final Log logger = LogFactory.getLog(ScriptClassLoader.class);
	
	private static final String FILE_TYPE_JAR = "jar";
	
	private ClassLoader oldClassLoader = null;
	
	private int SEARCHDEEPTH = 3;
	
	private String classPath = null;
	
	private List effectivePath = new ArrayList();
	
	public ScriptClassLoader(ClassLoader parent, String searchPath){
		super(parent);
		oldClassLoader=parent;
		setClassPath(searchPath);
	}
	
	public ScriptClassLoader(ClassLoader parent, String searchPath, int searchDeepth){
		super(parent);
		oldClassLoader=parent;
		SEARCHDEEPTH = searchDeepth;
		setClassPath(searchPath);
	}
	
	protected void setClassPath(String searchPath){
		classPath = searchPath;
		reCalcSearchPath();
	}
	
	private void reCalcSearchPath() {
		try{
			if(classPath==null || classPath.trim().length()==0){
				return ;
			}
		
			List clc = new ArrayList();
			String[] findPath = classPath.split(";");
			for(int i=0; i<findPath.length; i++){
				String path = findPath[i];
				path = path==null?null:path.trim();
				if(StringUtils.isEmpty(path)){
					continue ;
				}
			
				File testFile = new File(path);
				String fileType = getFileType(path);
				if(fileType!=null && fileType.equalsIgnoreCase(FILE_TYPE_JAR)){
					if(testFile.exists()){
						SearchPathWrapper spw = new SearchPathWrapper(path, SearchPathWrapper.FILE_TYPE_JAR);
						clc.add(spw);
					}
				}else if(testFile.isDirectory()){
					SearchPathWrapper spw = new SearchPathWrapper(testFile.getCanonicalPath(), SearchPathWrapper.FILE_TYPE_DIR);
					clc.add(spw);
				}
			}
		
			effectivePath.clear();
			effectivePath.addAll(clc);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}	
	}
	
	private String getFileType(String filePath){
		filePath = filePath==null?null:filePath.trim();
		if(StringUtils.isEmpty(filePath)){
			return null;
		}
		
		int iPlace = filePath.lastIndexOf(".");
		if(iPlace > 0){
			return filePath.substring(iPlace+1);
		}
		
		return null;
	}
	
	private InputStream findResource(File parent, String filePathSeg, int deepth) throws Exception {
		if(parent==null || !parent.isDirectory() || !parent.exists() || deepth<0){
			return null;
		}
		System.err.println("111�ɹ���["+"]����["+filePathSeg+"]");

		File directFindFile = new File(parent.getCanonicalPath()+"/"+filePathSeg);		
		if(directFindFile!=null && directFindFile.exists()){
			if(logger.isDebugEnabled()){
				logger.debug("�ɹ���["+directFindFile.getCanonicalPath()+"]����["+filePathSeg+"]");
				
			}
			return (new FileInputStream(directFindFile));
		}
	
		File[] childs = parent.listFiles(new FileFilter(){

			public boolean accept(File file) {
				try{
					String fileType = getFileType(file.getCanonicalPath());
					if(file.isDirectory() || fileType.equalsIgnoreCase(FILE_TYPE_JAR)){
						return true;	
					}
				}catch(Exception e){
					// do nothing...
				}
			
				return false;
			}
		
		});
		
		InputStream is = null;
		for(int i=0; i<childs.length; i++){
			File file = childs[i];
			String fileType = getFileType(file.getCanonicalPath());
			if(fileType!=null && fileType.equalsIgnoreCase(FILE_TYPE_JAR)){
				JarFile jarFile = new JarFile(file);
				Enumeration entries = jarFile.entries();
				JarEntry je = null;
				while(entries.hasMoreElements()){
					je = (JarEntry)entries.nextElement();					
					if(je.getName().equals(filePathSeg)){
						break ;
					}
					je = null;
				}
				if(je != null){
					if(logger.isDebugEnabled()){
						logger.debug("�ɹ���["+file.getCanonicalPath()+"]����["+filePathSeg+"]");
					}	
					return jarFile.getInputStream(je);
				}
			}else if(file.isDirectory()){
				File findFile = new File(file.getCanonicalPath()+"/"+filePathSeg);
				if(!findFile.exists()){
					is = findResource(file, filePathSeg, deepth-1);
				}else{
					is = new FileInputStream(findFile);
					if(logger.isDebugEnabled()){
						logger.debug("�ɹ���["+findFile.getCanonicalPath()+"]����["+filePathSeg+"]");
					}	
				}
				
				if(is != null){
					return is;
				}
			}
		}
		
		return null;
	}

	protected Class findClass(String name) throws ClassNotFoundException {
		 
		 
		byte[] data = loadClassData(name);
		if(data!=null && data.length>0){
			return defineClass(name, data, 0, data.length);
		}
		
		return null;
	}
	
	public byte[] loadClassData(String name) {		
		byte[] data = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try{
			if(CollectionUtils.isEmpty(effectivePath)){
				return null;
			}			

			String tempConvertPath = name.replace('.', '/');
			String filePathSeg = tempConvertPath+".class";
			for(Iterator itr=effectivePath.iterator(); itr.hasNext(); ){
				try{
					SearchPathWrapper spw = (SearchPathWrapper)itr.next();

					if(spw.getFileMode() == SearchPathWrapper.FILE_TYPE_JAR){
						JarFile jarFile = new JarFile(spw.getFilePath());
						Enumeration entries = jarFile.entries();
						JarEntry je = null;
						while(entries.hasMoreElements()){
							je = (JarEntry)entries.nextElement();					
							if(je.getName().equals(filePathSeg)){
								break ;
							}
							je = null;
						}
						if(je != null){
							if(logger.isDebugEnabled()){
								logger.debug("�ɹ���["+spw.getFilePath()+"]����["+name+"]");
							}
							is = jarFile.getInputStream(je);
							break ;
						}

					}else if(spw.getFileMode() == SearchPathWrapper.FILE_TYPE_DIR){
						File fileDir = new File(spw.getFilePath());
						if(!fileDir.exists()){//�ļ��в�����
							continue ;
						}
						
						File findFile = new File(spw.getFilePath()+"/"+filePathSeg);
						if(!findFile.exists()){//�ļ������ڣ���ȱ���
							is = findResource(fileDir, filePathSeg, SEARCHDEEPTH);
						}else{
							is = new FileInputStream(findFile);
							if(logger.isDebugEnabled()){								
								logger.debug("�ɹ���["+findFile.getCanonicalPath()+"]����["+name+"]");
							}
						}
						
						if(is != null){
							break ;
						}
					}
				}catch(Exception e){
					continue ;
				}
			}
			
			if(is == null){
				return null;
			}

			baos = new ByteArrayOutputStream();
			int ch = 0;
			while((ch = is.read()) != -1) {
				baos.write(ch);
			}
			
			data = baos.toByteArray();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}finally{
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(baos);
		}
		
		return data;
	}

	class SearchPathWrapper{
		
		static final int FILE_TYPE_JAR = 1;
		
		static final int FILE_TYPE_DIR = 2;
		
		private String filePath;
		
		private int fileMode = -1;
		
		public SearchPathWrapper(String filePath, int fileMode){
			this.filePath = filePath;
			this.fileMode = fileMode;
		}

		/**
		 * @return
		 */
		public int getFileMode() {
			return fileMode;
		}

		/**
		 * @return
		 */
		public String getFilePath() {
			return filePath;
		}

	}

}
