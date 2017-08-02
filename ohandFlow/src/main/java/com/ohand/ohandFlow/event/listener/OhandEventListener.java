package com.ohand.ohandFlow.event.listener;

import java.util.EventListener;

public interface OhandEventListener<T> extends EventListener {

	void onEvent(T objEvent);
	
}
