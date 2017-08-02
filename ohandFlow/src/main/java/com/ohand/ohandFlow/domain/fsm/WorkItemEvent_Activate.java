package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowWorkItemDomain;

public class WorkItemEvent_Activate extends WorkItemEvent {
	
	private static final long serialVersionUID = 5113477040251164861L;

	public WorkItemEvent_Activate(Object source) throws RuntimeException
	{
		super(source);
		this.workItemEventType=WorkItemEvent_Activate.EVENT_WORKITEM_ACTIVATE;
		if (!(source instanceof FlowWorkItemDomain))
		{
			throw new RuntimeException("事件source不是FlowWorkItemDomain!");
		}
		this.flowWorkItemDomain = (FlowWorkItemDomain) source;
	}

	@Override
	public void doDeal(WorkItemEvent event) {
		// TODO 
		System.out.println("事件保存方法:WorkItemEvent_Activate");
		System.out.println("事件保存方法:WorkItemEvent_Activate");
		System.out.println("事件保存方法:WorkItemEvent_Activate");
		System.out.println("事件保存方法:WorkItemEvent_Activate");
		System.out.println("事件保存方法:WorkItemEvent_Activate");
		
		FlowWorkItemDomain workItem=event.flowWorkItemDomain;
		workItem.setStatus(99999);
		System.out.println("事件保存方法:WorkItemEvent_Activate");
	}
	
}
