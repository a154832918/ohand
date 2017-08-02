package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowAttachFileDomain;

public interface FlowAttachFileService  {
	
	public FlowAttachFileDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map) ;

	public FlowAttachFileDomain insertObject(FlowAttachFileDomain vo) ;
	
	public Integer updateObject(FlowAttachFileDomain vo);

	public Integer deleteObjectById(Integer i) ;
	
}