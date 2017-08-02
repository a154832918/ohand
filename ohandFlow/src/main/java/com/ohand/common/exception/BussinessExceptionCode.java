package com.ohand.common.exception;

/**
 * @author richard
 * 组成模式：模块_功能标识_错误含义
 *
 */
public interface BussinessExceptionCode {
	
	/* 传入参数存在问题 */
	public  static final String  C_T_PARAM_ERROR_10000="10000_传入参数存在问题...";
	
	/* 流程编码与表单编码没有一一对应 */
	public  static final String  F_T_FORMCODE_NOT_MATCH_FLOWCODE="20000_流程编码与表单编码没有一一对应 ";
	
	/* 流程表单绑定异常，请联系管理员 */
	public  static final String  F_T_PARAM_ERROR_20001="20001_流程表单绑定异常，请联系管理员...";
	
	/* 流程表单绑定异常，请联系管理员 */
	public  static final String  F_T_PARAM_ERROR_20002="20002_流程已被其他表单绑定，请联系管理员...";	
	
	
}
