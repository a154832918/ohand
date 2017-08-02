package com.ohand.ohandFlow.script;

import java.io.File;

public interface OhandScriptConstants {

	public String CONVERT_FILE_PATH = System.getProperty("oapath")+File.separator+"convert";
	
	public String CLIENT_CONVERT_FILE_PATH = System.getProperty("oapath")+File.separator+"convert"+File.separator+"initform";
	
	public String JAR_MAPPING_PATH = "/META-INF/convert/";
	
	public int CACHE_MAX_OBJECT = 100;
	
	public String FORM_XML_TAG = "formInst";
	
	public String WORKITEM_XML_TAG = "workItem";
	
	public String FORM_EXTEND_FIELD_XML_TAG_START = "ext";
	
	public Integer TOP = new Integer(1);
	
	public Integer BOTTOM = new Integer(2);

	public Integer LEFT = new Integer(3);
	
	public Integer RIGHT = new Integer(4);
	
	public Integer MIDDLE = new Integer(5);
	
	public Integer HANDLER_TYPE = new Integer(1);
	
	public Integer VIEW_TYPE = new Integer(2); 
	
	public String BUSINESS_FLOW_JS = "_.js";
}
