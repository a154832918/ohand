package com.ohand.ohandFlow.domain.fsm;

import org.apache.log4j.Logger;

import com.ohand.ohandFlow.event.listener.OhandEventListener;

public class WorkItemEventListenerImpl implements
		OhandEventListener<WorkItemEvent> {

	private static Logger log = Logger.getLogger(WorkItemEventListenerImpl.class);
	
	public WorkItemEventListenerImpl() {
		super();
	}

	public void onEvent(WorkItemEvent objEvent) {
		try {
			objEvent.doDeal(objEvent);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("WorkItemEventListenerImpl:" + ex.getMessage());
		}
	}
	
}
