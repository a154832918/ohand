package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowInstDomain;

public class FlowInstEvent_Complete extends FlowInstEvent {

	private static final long serialVersionUID = 5051037675592043344L;

	public FlowInstEvent_Complete(Object source) throws RuntimeException
	{
		super(source);
		this.flowInstEventType=FlowInstEvent.EVENT_FLOWINST_COMPLETE;
		if (!(source instanceof FlowInstDomain))
		{
			throw new RuntimeException("事件source不是FlowInstDomain!");
		}
		this.flowInstDomain = (FlowInstDomain) source;
	}

	@Override
	public void doDeal(FlowInstEvent event) {
		// TODO 
		System.out.println("事件保存方法:FlowInstEvent_Complete");
		System.out.println("事件保存方法:FlowInstEvent_Complete");
		System.out.println("事件保存方法:FlowInstEvent_Complete");
		System.out.println("事件保存方法:FlowInstEvent_Complete");
		System.out.println("事件保存方法:FlowInstEvent_Complete");
		
		FlowInstDomain flowInst=event.flowInstDomain;
		flowInst.setStatus(99999);
		System.out.println("事件保存方法:FlowInstEvent_Complete");
	}
	
}
