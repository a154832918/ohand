package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowWorkItemDomain;

public class WorkItemEvent_Complete extends WorkItemEvent {
	
	private static final long serialVersionUID = -2644482375329899216L;

	public WorkItemEvent_Complete(Object source) throws RuntimeException
	{
		super(source);
		this.workItemEventType=WorkItemEvent_Complete.EVENT_WORKITEM_RETURN;
		if (!(source instanceof FlowWorkItemDomain))
		{
			throw new RuntimeException("事件source不是FlowWorkItemDomain!");
		}
		this.flowWorkItemDomain = (FlowWorkItemDomain) source;
	}

	@Override
	public void doDeal(WorkItemEvent event) {
		// TODO 
		System.out.println("事件保存方法:WorkItemEvent_Complete");
		System.out.println("事件保存方法:WorkItemEvent_Complete");
		System.out.println("事件保存方法:WorkItemEvent_Complete");
		System.out.println("事件保存方法:WorkItemEvent_Complete");
		System.out.println("事件保存方法:WorkItemEvent_Complete");
		
		FlowWorkItemDomain workItem=event.flowWorkItemDomain;
		workItem.setStatus(99999);
		System.out.println("事件保存方法:WorkItemEvent_Complete");
	}
	
}
