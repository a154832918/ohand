package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormElementScriptDomain;

public interface FormElementScriptService   {
	
	public FormElementScriptDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map) ;

	public FormElementScriptDomain insertObject(FormElementScriptDomain vo);
	
	public FormElementScriptDomain insertObjectWithVersion(FormElementScriptDomain vo);
	
	public Integer updateObject(FormElementScriptDomain vo) ;

	public Integer deleteObjectById(Integer i) ;

}