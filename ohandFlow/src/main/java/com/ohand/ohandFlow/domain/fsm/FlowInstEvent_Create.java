package com.ohand.ohandFlow.domain.fsm;

import com.ohand.ohandFlow.domain.FlowInstDomain;

public class FlowInstEvent_Create extends FlowInstEvent {
	
	private static final long serialVersionUID = -3317902435182724398L;
	private transient StateMachineProcessor stateMachineProcessor;

	public FlowInstEvent_Create(Object source) throws RuntimeException
	{
		super(source);
		this.flowInstEventType=FlowInstEvent.EVENT_FLOWINST_CREATE;
		if (!(source instanceof FlowInstDomain))
		{
			throw new RuntimeException("事件source不是FlowInstDomain!");
		}
		this.flowInstDomain = (FlowInstDomain) source;
	}

	@Override
	public void doDeal(FlowInstEvent event) {
		FlowInstDomain flowInst=event.flowInstDomain;
		flowInst.setStatus(IFlowInstState.FLOW_INST_STATUS_BEFORE_SAVE);
		stateMachineProcessor.FI_updateObject(flowInst);
	}
	
}
