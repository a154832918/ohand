package com.ohand.ohandFlow.domain.fsm;

import org.apache.log4j.Logger;

import com.ohand.ohandFlow.event.listener.OhandEventListener;

public class FlowInstEventListenerImpl implements
		OhandEventListener<FlowInstEvent> {

	private static Logger log = Logger.getLogger(FlowInstEventListenerImpl.class);
	
	public FlowInstEventListenerImpl() {
		super();
	}

	public void onEvent(FlowInstEvent objEvent) {
		try {
			objEvent.doDeal(objEvent);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("FlowInstEventListenerImpl:" + ex.getMessage());
		}
	}
	
}
