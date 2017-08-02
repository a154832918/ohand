package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowInstDomain;

public class FlowInstEvent_Delete extends FlowInstEvent {

	private static final long serialVersionUID = 1772163442209964945L;

	public FlowInstEvent_Delete(Object source) throws RuntimeException
	{
		super(source);
		this.flowInstEventType=FlowInstEvent.EVENT_FLOWINST_DELETE;
		if (!(source instanceof FlowInstDomain))
		{
			throw new RuntimeException("事件source不是FlowInstDomain!");
		}
		this.flowInstDomain = (FlowInstDomain) source;
	}

	@Override
	public void doDeal(FlowInstEvent event) {
		// TODO 
		System.out.println("事件保存方法:FlowInstEvent_Delete");
		System.out.println("事件保存方法:FlowInstEvent_Delete");
		System.out.println("事件保存方法:FlowInstEvent_Delete");
		System.out.println("事件保存方法:FlowInstEvent_Delete");
		System.out.println("事件保存方法:FlowInstEvent_Delete");
		
		FlowInstDomain flowInst=event.flowInstDomain;
		flowInst.setStatus(99999);
		System.out.println("事件保存方法:FlowInstEvent_Delete");
	}
	
}
