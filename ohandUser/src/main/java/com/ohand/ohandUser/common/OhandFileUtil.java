package com.ohand.ohandUser.common;

public class OhandFileUtil {
	
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
  
}
