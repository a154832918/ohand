package com.ohand.ohandFlow.domain.fsm;

import java.util.List;

/*
 *  工作项状态机
 *  外界直接调用 StateMachine的关键方法transition；实行状态的自动转变。
 *  2015年2月10日
 */
public class StateMachineAdmin {
	
	private static  StateMachineAdmin  instance=null;
	
	private StateMachineAdmin(){
	}
	
	public static StateMachineAdmin getInstance(){
		if(instance==null){
			instance=new StateMachineAdmin();
		}
		return instance;
	}
	
	/**获取表单控件对象集合*/
	public List getFormElementsByFormId(Integer formId){
		return null;
	}
	
	/**获取表单控件权限对象集合*/
	public List getFormElementsRightByFormIdAndActivityId(Integer formId,Integer activityId){
		return null;
	}
	
	/**获取流程Activity对象集合*/
	public List getFlowActivitiesByFlowId(Integer flowId){
		return null;
	}	
	
	/**获取流程Activity对象集合*/
	public List getFlowConnectorsByFlowId(Integer flowId){
		return null;
	}	
	
}
