package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormElementDataDomain;

public interface FormElementDataService {
	
	public FormElementDataDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);

	public FormElementDataDomain insertObject(FormElementDataDomain vo) ;

	public void insertObjectDomainList(List  list) ;
	
	public Integer updateObject(FormElementDataDomain vo);

	public Integer deleteObjectById(Integer i);
	
}