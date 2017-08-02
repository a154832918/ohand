package com.ohand.ohandFlow.domain.fsm;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.PFConstant;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowInstDomain;
import com.ohand.ohandFlow.domain.FlowWorkItemDomain;
import com.ohand.ohandFlow.domain.UserInfo;
import com.ohand.ohandFlow.event.EventFactory;

/*
 *  工作项状态机
 *  外界直接调用 StateMachine的关键方法transition；实行状态的自动转变。
 *  2015年2月10日
 */
@Service
public class StateMachineProcessor {
	
	private StateMachineProcessor(){
	}
	
	@Autowired
	private FlowWorkItemService flowWorkItemService;
	
	@Autowired
	private transient FlowInstService flowInstService;
	
	

	/**
	   * 根据Event参数，运行相应的状态。:当前工作项与事件类型
	   */
	  public static final IWorkItemState workItemTransition( WorkItemEvent event)  {
		FlowWorkItemDomain workItem=event.flowWorkItemDomain;
		System.out.println("++++++++++++++++StateMachineProcessor："+workItem.getStatus()+"++++++++++++++++++");  
		EventFactory factory = EventFactory.getInstance();
		WorkItemEventQueue queue =(WorkItemEventQueue) factory.getOhandEventQueue(PFConstant.EventQueue_WorkItemEvent);				
		queue.put(event);	
		System.out.println("事件保存方法:WorkItemEvent_Create--afterafterafterafterafter");
		IWorkItemState currentState = null;
		System.out.println("++++++++++++++++StateMachineProcessor："+workItem.getStatus()+"++++++++++++++++++");
	    return currentState;
	 }
	 
	  /**
	   * 根据Event参数，运行相应的状态。:当前流程实例与事件类型
	   */
	  public static final IFlowInstState flowInstTransition( FlowInstEvent event)  {
		FlowInstDomain flowInst=event.flowInstDomain;
		System.out.println("+++IFlowInstState+++++++++++++StateMachineProcessor："+flowInst.getStatus()+"++++++++++++++++++");  
		EventFactory factory = EventFactory.getInstance();
		FlowInstEventQueue queue =(FlowInstEventQueue) factory.getOhandEventQueue(PFConstant.EventQueue_FlowInstEvent);				
		queue.put(event);	
		System.out.println("事件保存方法:FlowInstEvent_Create--afterafterafterafterafter");
		IFlowInstState currentState = null;
		System.out.println("++++++IFlowInstState++++++++++StateMachineProcessor："+flowInst.getStatus()+"++++++++++++++++++");
	    return currentState;
	 }
	  
	public FlowWorkItemDomain FWI_getObjectVOById(Integer id) {
		return flowWorkItemService.getObjectVOById(id);
	}
	
	public List FWI_getObjectMapList(Map map) {
		return flowWorkItemService.getObjectMapList(map);
	}
	
	public List FWI_getObjectVOList(Map map) {
		return flowWorkItemService.getObjectVOList(map);
	}
	
	public Pagination FWI_getObjectVOListPage(Map map) {
		return flowWorkItemService.getObjectVOListPage(map);
	}
	
	public void FWI_insertObject(FlowWorkItemDomain vo) {
		flowWorkItemService.insertObject(vo);
	}
	
	public void FWI_saveInst(List list,String workItemId,UserInfo user,Integer flowInstId) throws BussinessException, IOException {
		flowWorkItemService.saveInst(list, workItemId,user,flowInstId);
	}
	
	public Integer FWI_updateObject(FlowWorkItemDomain vo) {
		return flowWorkItemService.updateObject(vo);
	}
	
	public Integer FWI_deleteObjectById(Integer i) {
		return flowWorkItemService.deleteObjectById(i);
	}
	
	public FlowInstDomain FI_getObjectVOById(Integer id) {
		return flowInstService.getObjectVOById(id);
	}
	
	public List FI_getObjectMapList(Map map) {
		return flowInstService.getObjectMapList(map);
	}

	public List FI_getObjectVOList(Map map) {
		return flowInstService.getObjectVOList(map);
	}
	
	public Pagination FI_getObjectVOListPage(Map map) {
		return flowInstService.getObjectVOListPage(map);
	}
	
	public void FI_insertObject(FlowInstDomain vo) {
		flowInstService.insertObject(vo);
	}
	
	public void FI_saveInst(FlowInstDomain vo,List elementDataList,UserInfo userInfo) {
		flowInstService.saveInst(vo, elementDataList,userInfo);
	}
	
	public Integer FI_updateObject(FlowInstDomain vo) {
		return flowInstService.updateObject(vo);
	}
	
	public Integer FI_deleteObjectById(Integer i) {
		return flowInstService.deleteObjectById(i);
	}
	
	
}
