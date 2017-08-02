package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormRFlowDomain;

public interface FormRFlowService  {

	public FormRFlowDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map) ;
	
	public List missiveTemplate(Map map) ;
	
	public Pagination getObjectVOListPage(Map map) ;

	public FormRFlowDomain insertObject(FormRFlowDomain vo);
	
	public Integer updateObject(FormRFlowDomain vo);

	public Integer deleteObjectById(Integer i);
	
	public Integer deleteObjectByFormCode(Integer formCode);
	
	public void saveBinding(FormRFlowDomain formRFlow);
	
}