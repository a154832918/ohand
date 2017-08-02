package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowWorkItemDomain;

public abstract class WorkItemEvent extends FlowEvent<WorkItemEvent> {
	
	public WorkItemEvent(Object source) {
		super(source);
	}

	private static final long serialVersionUID = 5288808441190724821L;

	protected FlowWorkItemDomain flowWorkItemDomain;
	
	protected String workItemEventType;
	
	public  int getEventType(){
		return EVENT_TYPE_WORKITEM;
	}
	
	public String getWorkItemEventType(){
		return workItemEventType;
	}
	
	//工作项创建
	public static final String EVENT_WORKITEM_CREATE="create";
	// 工作项完成
	public static final String EVENT_WORKITEM_COMPLETE="complete";
	// 工作项退回
	public static final String EVENT_WORKITEM_RETURN="return";
	// 工作项回收	
	public static final String EVENT_WORKITEM_RECLAIM="reclaim";
	// 工作项激活
	public static final String EVENT_WORKITEM_ACTIVATE="activate";
	// 工作项删除
	public static final String EVENT_WORKITEM_DELETE="delete";
	// 工作项重做
	public static final String EVENT_WORKITEM_RESET="reset";
	// 工作项合并
	public static final String EVENT_WORKITEM_MERGE="merge";
	
}
