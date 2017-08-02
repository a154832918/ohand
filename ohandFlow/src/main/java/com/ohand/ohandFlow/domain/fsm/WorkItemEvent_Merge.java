package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowWorkItemDomain;

public class WorkItemEvent_Merge extends WorkItemEvent {

	private static final long serialVersionUID = 2450806502344855031L;

	public WorkItemEvent_Merge(Object source) throws RuntimeException
	{
		super(source);
		this.workItemEventType=WorkItemEvent_Merge.EVENT_WORKITEM_MERGE;
		if (!(source instanceof FlowWorkItemDomain))
		{
			throw new RuntimeException("事件source不是FlowWorkItemDomain!");
		}
		this.flowWorkItemDomain = (FlowWorkItemDomain) source;
	}

	@Override
	public void doDeal(WorkItemEvent event) {
		// TODO 
		System.out.println("事件保存方法:WorkItemEvent_Merge");
		System.out.println("事件保存方法:WorkItemEvent_Merge");
		System.out.println("事件保存方法:WorkItemEvent_Merge");
		System.out.println("事件保存方法:WorkItemEvent_Merge");
		System.out.println("事件保存方法:WorkItemEvent_Merge");
		
		FlowWorkItemDomain workItem=event.flowWorkItemDomain;
		workItem.setStatus(99999);
		System.out.println("事件保存方法:WorkItemEvent_Merge");
	}
	
}
