package com.ohand.spring01.framework.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.ohand.spring01.framework.Configuration;

public class BaseController {

	
	public static final String RESPONCE_TYPE_TEXT = "text";
	
	public static final String RESPONCE_TYPE_HTML = "html";
	
	public static final String RESPONCE_TYPE_JSON = "json";
	
	public static final String RESPONCE_TYPE_XML = "xml";
	
	public static final int SUCCESS = Configuration.Status.STATUS_OK;
	public static final int FAIL = Configuration.Status.STATUS_FAIL;

	public static String getAllErrors(Errors errors) {
		StringBuilder builder = new StringBuilder();
		for (ObjectError error : errors.getAllErrors()) {
			builder.append(error.getDefaultMessage()).append("\n");
		}
		return builder.toString();
	}
	
	public void initJsonResponse(HttpServletResponse response, String json,String responseType)
			throws IOException {
	    String contentType = null;
        if (RESPONCE_TYPE_HTML.equalsIgnoreCase(responseType)) {
            contentType = "text/html;charset=utf-8";
        } else if (RESPONCE_TYPE_JSON.equalsIgnoreCase(responseType)) {
            contentType = "text/json;charset=utf-8";
        } else if (RESPONCE_TYPE_TEXT.equalsIgnoreCase(responseType)) {
            contentType = "text/plain;charset=utf-8";
        } else if (RESPONCE_TYPE_XML.equalsIgnoreCase(responseType)) {
            contentType = "text/xml;charset=utf-8";
        } else {
            contentType = "text/html;charset=utf-8";
        }
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType(contentType);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
	}
	
}
