package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowCategoryDomain;

public interface FlowCategoryService {
	
	public FlowCategoryDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map) ;

	public Pagination getObjectVOListPage(Map map) ;

	public Pagination getObjectVOListTreePage(Map map) ;
	
	public FlowCategoryDomain insertObject(FlowCategoryDomain vo) ;

	public Integer updateObject(FlowCategoryDomain vo);

	public Integer deleteObjectById(Integer i);
	
	public List hasChild(Map map);
	
	public String id2Name(Integer i);
	
}