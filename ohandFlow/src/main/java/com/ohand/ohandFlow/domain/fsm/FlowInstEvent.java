package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowInstDomain;

public abstract class FlowInstEvent extends FlowEvent<FlowInstEvent> {
	
	private static final long serialVersionUID = 5163941111149518494L;
	
	public FlowInstEvent(Object source) {
		super(source);
	}

	protected FlowInstDomain flowInstDomain;
	
	protected String flowInstEventType;
	
	public  int getEventType(){
		return EVENT_TYPE_FLOWINST;
	}
	
	public String getFlowInstEventType(){
		return flowInstEventType;
	}
	
	//流程实例创建
	public static final String EVENT_FLOWINST_CREATE="create";
	//流程实例完成
	public static final String EVENT_FLOWINST_COMPLETE="complete";
	//流程实例删除
	public static final String EVENT_FLOWINST_DELETE="delete";
	//流程实例激活
	public static final String EVENT_FLOWINST_ACTIVATE="activate";
}
