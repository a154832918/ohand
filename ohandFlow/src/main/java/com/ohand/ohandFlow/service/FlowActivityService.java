package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowActivityDomain;

public interface FlowActivityService{

	public FlowActivityDomain getObjectVOById(Integer id);

	public List getObjectMapList(Map map) ;

	public List getObjectVOList(Map map) ;

	public Pagination getObjectVOListPage(Map map);

	public FlowActivityDomain insertObject(FlowActivityDomain vo);
	
	public Integer updateObject(FlowActivityDomain vo) ;
	
	public Integer deleteObjectById(Integer i) ;

}
