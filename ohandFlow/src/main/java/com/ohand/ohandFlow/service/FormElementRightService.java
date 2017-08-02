package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormElementRightDomain;

public interface FormElementRightService  {
	
	public FormElementRightDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map) ;

	public FormElementRightDomain insertObject(FormElementRightDomain vo);
	
	public Integer updateObject(FormElementRightDomain vo) ;

	public Integer deleteObjectById(Integer i) ;

	public void ohandPluginPageItemPermissionSave(List list);
	
}