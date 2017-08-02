package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowConnectorDomain;

public interface FlowConnectorService {

	public FlowConnectorDomain getObjectVOById(Integer id);

	public List getObjectMapList(Map map) ;

	public List getObjectVOList(Map map) ;

	public Pagination getObjectVOListPage(Map map) ;

	public FlowConnectorDomain insertObject(FlowConnectorDomain vo) ;

	public Integer updateObject(FlowConnectorDomain vo) ;

	public Integer deleteObjectById(Integer i);

}