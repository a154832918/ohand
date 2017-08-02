package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowWorkItemDomain;

public class WorkItemEvent_Reclaim extends WorkItemEvent {
	
	private static final long serialVersionUID = -4209086606287501443L;

	public WorkItemEvent_Reclaim(Object source) throws RuntimeException
	{
		super(source);
		this.workItemEventType=WorkItemEvent_Reclaim.EVENT_WORKITEM_RECLAIM;
		if (!(source instanceof FlowWorkItemDomain))
		{
			throw new RuntimeException("事件source不是FlowWorkItemDomain!");
		}
		this.flowWorkItemDomain = (FlowWorkItemDomain) source;
	}

	@Override
	public void doDeal(WorkItemEvent event) {
		// TODO 
		System.out.println("事件保存方法:WorkItemEvent_Reclaim");
		System.out.println("事件保存方法:WorkItemEvent_Reclaim");
		System.out.println("事件保存方法:WorkItemEvent_Reclaim");
		System.out.println("事件保存方法:WorkItemEvent_Reclaim");
		System.out.println("事件保存方法:WorkItemEvent_Reclaim");
		
		FlowWorkItemDomain workItem=event.flowWorkItemDomain;
		workItem.setStatus(99999);
		System.out.println("事件保存方法:WorkItemEvent_Reclaim");
	}
	
}
