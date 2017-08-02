package com.ohand.ohandFlow.common;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author richard
 *
 */
public class MD5Util {
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messagedigest = null;
	static{
	   try{
	    messagedigest = MessageDigest.getInstance("MD5");
	   }catch(NoSuchAlgorithmException nsaex){
	    System.err.println(MD5Util.class.getName()+"初始化失败，MessageDigest不支持MD5Util。");
	    nsaex.printStackTrace();
	   }
	}

	public static String getFileMD5String(File file) {
	        if (!file.isFile()){
	          return null;
	        }
	        MessageDigest digest = null;
	        FileInputStream in=null;
	        byte buffer[] = new byte[1024];
	        int len;
	        try {
	          digest = MessageDigest.getInstance("MD5");
	          in = new FileInputStream(file);
	          while ((len = in.read(buffer, 0, 1024)) != -1) {
	            digest.update(buffer, 0, len);
	          }
	          in.close();
	        } catch (Exception e) {
	          e.printStackTrace();
	          return null;
	        }
	     BigInteger bigInt = new BigInteger(1, digest.digest());
	     return bigInt.toString(16);
	}

	public static String getMD5String(String s) {
	   return getMD5String(s.getBytes());
	}

	public static String getMD5String(byte[] bytes) {
	   messagedigest.update(bytes);
	   return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
	   return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
	   StringBuffer stringbuffer = new StringBuffer(2 * n);
	   int k = m + n;
	   for (int l = m; l < k; l++) {
	    appendHexPair(bytes[l], stringbuffer);
	   }
	   return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
	   char c0 = hexDigits[(bt & 0xf0) >> 4];
	   char c1 = hexDigits[bt & 0xf];
	   stringbuffer.append(c0);
	   stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) {
	   String s = getMD5String(password);
	   return s.equals(md5PwdStr);
	}
	
	public static String getMD5(String source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(source.getBytes());
			byte tmp[] = md5.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
	
}
