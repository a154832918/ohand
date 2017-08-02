package com.ohand.ohandFlow.domain.fsm;

import java.util.EventObject;

public abstract class FlowEvent<T> extends EventObject {
	
	public FlowEvent(Object source) {
		super(source);
	}
	
	private static final long serialVersionUID = -5978539614988753827L;
	// 工作项
	public static final int EVENT_TYPE_WORKITEM=1;
	// 流程实例
	public static final int EVENT_TYPE_FLOWINST=2;
	// 事件处理方法
	public abstract void doDeal(T event);
	
}
