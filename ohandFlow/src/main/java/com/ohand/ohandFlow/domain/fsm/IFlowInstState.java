package com.ohand.ohandFlow.domain.fsm;

public interface IFlowInstState {
	
	public static final Integer FLOW_INST_STATUS_BEFORE_SAVE = new Integer(-1);
	
	//开始         一个流程实例已经生成，但该流程实例目前还没有满足开始执行的条件。
	public static final Integer FLOW_INST_STATUS_INACTIVE = new Integer(0);
	//准备运行     该流程实例已经可以执行，但还不满足开始执行第一个步骤并生成任务项的条件。
	public static final Integer FLOW_INST_STATUS_RUNNING = new Integer(1);	
	//运行中     一个或多个步骤已经开始执行。
	public static final Integer FLOW_INST_STATUS_ACTIVE = new Integer(2);		
	//挂起    该流程实例正在运行，并处于静止状态，除非有一个“”的命令或外部事件使该流程实例回到准备运行状态，
	// 否则所有步骤都不会执行。
	public static final Integer FLOW_INST_STATUS_SUSPENDED = new Integer(3);	
	// 结束  该流程实例执行已经完成，并且满足了结束该实例的条件。
	public static final Integer FLOW_INST_STATUS_COMPLETED = new Integer(4);
	//终止  该流程实例在正常结束前被迫终止（比如出现错误或异常情况）。
	public static final Integer FLOW_INST_STATUS_TERMINATED = new Integer(5);
	
}