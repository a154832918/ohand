
package com.ohand.ohandFlow.domain.fsm;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowInstDomain;
import com.ohand.ohandFlow.domain.FormElementDataDomain;
import com.ohand.ohandFlow.domain.UserInfo;

public interface FlowInstService  {

	public FlowInstDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);
	
	public FlowInstDomain insertObject(FlowInstDomain vo) ;

	/**
	 * 一、保存表单数据
	 * 二、保存用户创建工作项
	 */
	public void saveInst(FlowInstDomain vo,List elementDataList,UserInfo userInfo);
	
	public boolean isExistsInList(List<FormElementDataDomain> list,FormElementDataDomain vo);
	
	public Integer updateObject(FlowInstDomain vo) ;

	public Integer deleteObjectById(Integer i);

}