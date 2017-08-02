package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowWorkItemDomain;

public class WorkItemEvent_Reset extends WorkItemEvent {

	private static final long serialVersionUID = -7101856681171125760L;

	public WorkItemEvent_Reset(Object source) throws RuntimeException
	{
		super(source);
		this.workItemEventType=WorkItemEvent_Reset.EVENT_WORKITEM_RESET;
		if (!(source instanceof FlowWorkItemDomain))
		{
			throw new RuntimeException("事件source不是FlowWorkItemDomain!");
		}
		this.flowWorkItemDomain = (FlowWorkItemDomain) source;
	}

	@Override
	public void doDeal(WorkItemEvent event) {
		// TODO 
		System.out.println("事件保存方法:WorkItemEvent_Reset");
		System.out.println("事件保存方法:WorkItemEvent_Reset");
		System.out.println("事件保存方法:WorkItemEvent_Reset");
		System.out.println("事件保存方法:WorkItemEvent_Reset");
		System.out.println("事件保存方法:WorkItemEvent_Reset");
		
		FlowWorkItemDomain workItem=event.flowWorkItemDomain;
		workItem.setStatus(99999);
		System.out.println("事件保存方法:WorkItemEvent_Reset");
	}
	
}
