package com.ohand.ohandFlow.domain.fsm;

import javax.annotation.Resource;

import com.ohand.ohandFlow.domain.FlowInstDomain;

public class FlowInstEvent_Activate extends FlowInstEvent {
	
	private static final long serialVersionUID = 3611470617406163351L;
	
	@Resource
	private transient StateMachineProcessor stateMachineProcessor;
	
	public FlowInstEvent_Activate(Object source) throws RuntimeException
	{
		super(source);
		this.flowInstEventType=FlowInstEvent.EVENT_FLOWINST_ACTIVATE;
		if (!(source instanceof FlowInstDomain))
		{
			throw new RuntimeException("事件source不是FlowInstDomain!");
		}
		this.flowInstDomain = (FlowInstDomain) source;
	}

	@Override
	public void doDeal(FlowInstEvent event) {		
		FlowInstDomain flowInst=event.flowInstDomain;
		flowInst.setStatus(IFlowInstState.FLOW_INST_STATUS_ACTIVE);
		stateMachineProcessor.FI_updateObject(flowInst);
	}

	public StateMachineProcessor getStateMachineProcessor() {
		return stateMachineProcessor;
	}

	public void setStateMachineProcessor(StateMachineProcessor stateMachineProcessor) {
		this.stateMachineProcessor = stateMachineProcessor;
	}
	
}
