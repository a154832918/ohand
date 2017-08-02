package com.ohand.common.exception;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-10-25
 * 提交测试
 */
public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 666442538765365576L;

	private String key;// 错误编码

	private Object[] values;// 错误信息集合
	
	private String message;//单个信息 

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public String getKey() {
		return key;
	}

	public Object[] getValues() {
		return values;
	}

	public BussinessException() {
		super();
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
		this.message=message;
	}

	public BussinessException(String message) {
		super(message);
		this.message=message;
	}

	public BussinessException(Throwable cause) {
		super(cause);
	}

	public BussinessException(String message, String key) {
		super(message);
		this.message=message;
		this.key = key;
	}

	public BussinessException(String message, String key, Object values) {
		super(message);
		this.key = key;
		this.message=message;
		this.values = new Object[] { values };
	}

	public BussinessException(String message, String key, Object[] values) {
		super(message);
		this.key = key;
		this.message=message;
		this.values = values;
	}
}
