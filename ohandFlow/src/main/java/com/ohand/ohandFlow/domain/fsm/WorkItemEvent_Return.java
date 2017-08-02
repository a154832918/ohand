package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowWorkItemDomain;

public class WorkItemEvent_Return extends WorkItemEvent {
	
	private static final long serialVersionUID = -2644482375329899216L;
	
	public WorkItemEvent_Return(Object source) throws RuntimeException
	{
		super(source);
		this.workItemEventType=WorkItemEvent_Return.EVENT_WORKITEM_COMPLETE;
		if (!(source instanceof FlowWorkItemDomain))
		{
			throw new RuntimeException("事件source不是FlowWorkItemDomain!");
		}
		this.flowWorkItemDomain = (FlowWorkItemDomain) source;
	}

	@Override
	public void doDeal(WorkItemEvent event) {
		// TODO 
		System.out.println("事件保存方法:WorkItemEvent_Return");
		System.out.println("事件保存方法:WorkItemEvent_Return");
		System.out.println("事件保存方法:WorkItemEvent_Return");
		System.out.println("事件保存方法:WorkItemEvent_Return");
		System.out.println("事件保存方法:WorkItemEvent_Return");
		
		FlowWorkItemDomain workItem=event.flowWorkItemDomain;
		workItem.setStatus(99999);
		System.out.println("事件保存方法:WorkItemEvent_Return");
	}
	
}
