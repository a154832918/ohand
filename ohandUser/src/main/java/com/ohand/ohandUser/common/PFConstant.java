package com.ohand.ohandUser.common;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-10-25
 */
public class PFConstant {
	
    //连接点协议类型：HTTP
    public static final int PROTOCAL_HTTP = 1;
    //连接点协议类型：HTTPS
    public static final int PROTOCAL_HTTPS = 2;
    //连接点认证类型：令牌类型
	 //连接点协议类型：与服务器相同
    public static final int PROTOCAL_AUTO = 0;
    //连接点认证类型：服务器模拟表单类型
    public static final int AUTH_FORM = 0;
    //连接点认证类型：令牌类型
    public static final int AUTH_TOKEN = 1;

    //连接点认证类型：客户端模拟表单类型
    public static final int AUTH_CLINET_FORM = 3;
	
	public static final String PF_ENCODING="UTF-8";
	
    public static final String ROOT_CSSPATH = "/ohandUser/styles/";
    
    public static final String JAVASCRIPT_PATH = "/ohandUser/javascripts/";
    
    public static final String CSS_PATH = "/ohandUser/styles/default/";
    
    public static final String IMAGE_PATH = "/ohandUser/images/";
    
    public static final String TOKEN = "ohand";
	
    /** session存放令牌的变量名称 */
    public static String SESSION_TOKEN_KEY = "IV_TOKEN";
    
	// 缓存最大对象个数
	public static final int CACHE_MAX_OBJECT = 100;
	// sql 分页标识符的z正则表达式
	public static final String SQL_PAGE_ENDSTRING = ".*Pagination$";
	
	public static final String DIRT_IS_YES="1";

	public static final String DIRT_IS_NO="2";
	
	public static final String SESSION_USER_KEY="pfuser";
	
	public static final String CACHE_FLOW_CATEGROY_NAME="flowCategoryName";
	
	public static final String COOKIE_USER_KEY="ck_lr_login_session";
	
	public static final String COOKIE_USER_NAME="ck_lr_login_username";
	
	public static final String CACHE_COMMON_DIRECTORY_NAME="cacheCommonDirectory";//数据字典缓存
	
	public static final String CACHE_COMMON_NAME="cacheCommon";//地址本缓存
	
	public static final String CACHE_CONSTANT_CONFIG_NAME="cacheConstantConfig";//常量配置缓存
	
	public static final String CACHE_PERMISSION_ITEM_NAME="cachePermissionItem";//权限项配置缓存
	
	public static final String CACHE_INFO_EDIT_PAGE="infoEditPage";//信息编辑页面缓存
	
	public static final String CACHE_TEMPLATE_CONFIG="templateConfig";//系统模板缓存
	
	public static final String DICT_SHOW_TYPE_CHECKBOX="checkbox";
	
	public static final String DICT_SHOW_TYPE_RADIO="radio";
	
	public static final String DICT_SHOW_TYPE_SELECT="select";
	
	public static final String returnQueryNum="2147483647";
	
	public static final String cacheSynchronizer="com.ohand.common.cache.JGroupsSynchronizer";
	
	public static final String flowAttachFile="D:\\ohand\\ohand_doc\\ohandfile\\flowattach";
	
	public static final String personImageFile="D:\\ohand\\ohand_doc\\ohandfile\\personImage";
	
	public static final String INFO_PATH="D:\\ohand\\ohand_doc\\ohandfile\\info";
	
	public static final String  BPMN_PATH="D:\\ohand\\ohand_doc\\ohandfile\\bpmn";
	
    public static String FILE_DOWNLOAD_PREFIX = "/cms/File/Download.do?id=";
	
	public static String JAR_MAPPING_PATH = "/META-INF/convert/";
	
	public static String getOhandebRoot() {
		StringBuffer webRootBuffer = new StringBuffer();
//		webRootBuffer.append("http://");
//		webRootBuffer.append(ConfigManager.deployUrl);
//		if (ConfigManager.deployPath != null && !ConfigManager.deployPath.equals("")) {
//			webRootBuffer.append(ConfigManager.deployPath + "/");
//		}else {
//			webRootBuffer.append("/");
//		}
		return webRootBuffer.toString();
	}
	
	public static String EventQueue_OhandLog = "ohandLog";
	
	public static String EventQueue_WorkItemEvent = "workItemEvent";
	
	public static String EventQueue_FlowInstEvent = "flowInstEvent";
	
}
