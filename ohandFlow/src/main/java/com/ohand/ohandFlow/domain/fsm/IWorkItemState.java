package com.ohand.ohandFlow.domain.fsm;

public interface IWorkItemState {
	
	//新收到
	public static final Integer WORK_ITEM_STATUS_NEW = new Integer(0);
	//等待中
	public static final Integer WORK_ITEM_STATUS_PENDING = new Integer(1);
	//已保存
	public static final Integer WORK_ITEM_STATUS_SAVED = new Integer(2);
	//已阅读
	public static final Integer WORK_ITEM_STATUS_READ = new Integer(3);
	//被催办
	public static final Integer WORK_ITEM_STATUS_URGED = new Integer(4);	
	//被退回
	public static final Integer WORK_ITEM_STATUS_RETURNED = new Integer(5);
	//已收回
	public static final Integer WORK_ITEM_STATUS_RECLAIMED = new Integer(6);
	//已过期
	public static final Integer WORK_ITEM_STATUS_OVER_DEADLINE = new Integer(7);
	//已处理
	public static final Integer WORK_ITEM_STATUS_COMPLETED = new Integer(8);
	
}