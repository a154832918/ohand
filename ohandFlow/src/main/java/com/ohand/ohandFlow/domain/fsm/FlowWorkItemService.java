package com.ohand.ohandFlow.domain.fsm;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowWorkItemDomain;
import com.ohand.ohandFlow.domain.UserInfo;

public interface FlowWorkItemService {

	public FlowWorkItemDomain getObjectVOById(Integer id) ;

	public List getObjectMapList(Map map) ;

	public List getObjectVOList(Map map) ;

	public Pagination getObjectVOListPage(Map map);

	public FlowWorkItemDomain insertObject(FlowWorkItemDomain vo);

	/*
	 * 执行脚本   2014年6月29日
	 * parameter：
	 * 	 list:即将产生的工作项集合
	 * 	 workItemId:即将完成的工作项
	 */
	public void saveInst(List list,String workItemId,UserInfo user,Integer flowInstId) throws BussinessException, IOException ;
	
	public Integer updateObject(FlowWorkItemDomain vo);

	public Integer deleteObjectById(Integer i) ;
	
	// TODO 拼凑SQL
	public String packWorkItemSQL(String flowCode);
	
}