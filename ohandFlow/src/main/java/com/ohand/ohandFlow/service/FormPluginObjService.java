package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormPluginObjDomain;

public interface FormPluginObjService  {
	
	public FormPluginObjDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map);
	
	public FormPluginObjDomain insertObject(FormPluginObjDomain vo);
	
	public Integer updateObject(FormPluginObjDomain vo);
	
	public Integer deleteObjectById(Integer i) ;
	
	public int getNextFlowCode() ;
	
	
}