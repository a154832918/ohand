package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowWorkItemDomain;

public class WorkItemEvent_Delete extends WorkItemEvent {

	private static final long serialVersionUID = 5573621815690354152L;

	public WorkItemEvent_Delete(Object source) throws RuntimeException
	{
		super(source);
		this.workItemEventType=WorkItemEvent_Delete.EVENT_WORKITEM_DELETE;
		if (!(source instanceof FlowWorkItemDomain))
		{
			throw new RuntimeException("事件source不是FlowWorkItemDomain!");
		}
		this.flowWorkItemDomain = (FlowWorkItemDomain) source;
	}

	@Override
	public void doDeal(WorkItemEvent event) {
		// TODO 
		System.out.println("事件保存方法:WorkItemEvent_Delete");
		System.out.println("事件保存方法:WorkItemEvent_Delete");
		System.out.println("事件保存方法:WorkItemEvent_Delete");
		System.out.println("事件保存方法:WorkItemEvent_Delete");
		System.out.println("事件保存方法:WorkItemEvent_Delete");
		
		FlowWorkItemDomain workItem=event.flowWorkItemDomain;
		workItem.setStatus(99999);
		System.out.println("事件保存方法:WorkItemEvent_Delete");
	}
	
}
