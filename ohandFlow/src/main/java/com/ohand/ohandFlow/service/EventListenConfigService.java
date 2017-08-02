package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.EventListenConfigDomain;


public interface EventListenConfigService {

	public EventListenConfigDomain getObjectVOById(Integer id) ;
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map) ;
	
	public EventListenConfigDomain insertObject(EventListenConfigDomain vo);
	
	public Integer updateObject(EventListenConfigDomain vo) ;
	
	public Integer deleteObjectById(Integer i) ;
	
}