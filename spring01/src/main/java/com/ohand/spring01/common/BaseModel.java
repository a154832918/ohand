package com.ohand.spring01.common;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-10-25
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = -3897270223767344905L;

	protected Log logger = LogFactory.getLog(getClass());
	
	public static int pageSize = 15;
	public static int pageIndex = 1;
	
	String sort;
	String dir;

	protected String userSymbol;
	protected String orderBy;
	protected String descOrAsc;
	/**字符串转换成时间的格式*/
	public  static String  DATEFORMAT = "yyyy-MM-dd HH:mm";  
	/**VO创建时间*/
	private  Date  createDate;
	/**VO最近修改时间*/
	private  Date  lastModifiedDate;
	/**扩展属性值*/
	private  Map attrs = new HashMap();
	
	public Object get(Object key){
		return attrs.get(key);
	}
	
	/**
	 * @return Returns the attrs.
	 */
	public Map getAttrs() {
		return attrs;
	}

	/**
	 * @return 返回 createDate。
	 */
	public Date getCreateDate() {
		return createDate;
	}
	
	/**
	 * @return
	 */
	public String getDescOrAsc() {
		if ( this.descOrAsc == null ) return null ;
				else return "".equals(this.descOrAsc.trim())?null:this.descOrAsc;
	}	
	public String getDir() {
		return dir;
	}

	/**
	 * @return 返回 lastModifiedDate。
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	
	public Log getLogger() {
		return logger;
	}

	/**
	 * @return
	 */
	public String getOrderBy() {
		if ( this.orderBy == null ) return null ;
				else return "".equals(this.orderBy.trim())?null:this.orderBy;
	}


	
	public String getSort() {
		return sort;
	}

	/**
	 * @return
	 */
	public String getUserSymbol() {
		if ( this.userSymbol == null ) return null ;
				else return "".equals(this.userSymbol.trim())?null:this.userSymbol;
	}

	public Object put(Object key,Object value){
		return attrs.put(key,value);
	}
	/**
	 * @param attrs The attrs to set.
	 */
	public void setAttrs(Map attrs) {
		this.attrs = attrs;
	}
	/**
	 * @param createDate 要设置的 createDate。
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @param string
	 */
	public void setDescOrAsc(String string) {
		this.descOrAsc = string == null?null:string.trim();
	}
	
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * @param lastModifiedDate 要设置的 lastModifiedDate。
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	/**
	 * @param string
	 */
	public void setOrderBy(String string) {
		this.orderBy = string == null?null:string.trim();
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @param string
	 */
	public void setUserSymbol(String string) {
		this.userSymbol = string == null?null:string.trim();
	}
}