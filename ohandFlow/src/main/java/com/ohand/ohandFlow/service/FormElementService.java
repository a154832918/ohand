package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormElementDomain;

public interface FormElementService {

	public FormElementDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map)  ;
	
	public List getObjectVOList(Map map)  ;
	
	public Pagination getObjectVOListPage(Map map)  ;

	public FormElementDomain insertObject(FormElementDomain vo)  ;
	
	public Integer updateObject(FormElementDomain vo)  ;
	
	public Integer deleteObjectById(Integer i) ;
	
	
}