package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowWorkItemDomain;

public class WorkItemEvent_Create extends WorkItemEvent {
	
	private static final long serialVersionUID = -7211223590201197300L;

	public WorkItemEvent_Create(Object source) throws RuntimeException
	{
		super(source);
		this.workItemEventType=WorkItemEvent_Create.EVENT_WORKITEM_CREATE;
		if (!(source instanceof FlowWorkItemDomain))
		{
			throw new RuntimeException("事件source不是FlowWorkItemDomain!");
		}
		this.flowWorkItemDomain = (FlowWorkItemDomain) source;
	}

	@Override
	public void doDeal(WorkItemEvent event) {
		// TODO 
		System.out.println("事件保存方法:WorkItemEvent_Create");
		System.out.println("事件保存方法:WorkItemEvent_Create");
		System.out.println("事件保存方法:WorkItemEvent_Create");
		System.out.println("事件保存方法:WorkItemEvent_Create");
		System.out.println("事件保存方法:WorkItemEvent_Create");
		
		FlowWorkItemDomain workItem=event.flowWorkItemDomain;
		workItem.setStatus(99999);
		System.out.println("事件保存方法:WorkItemEvent_Create111");
	}
	
}
