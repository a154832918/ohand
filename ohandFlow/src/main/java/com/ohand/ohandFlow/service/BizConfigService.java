package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.BizConfigDomain;

public interface BizConfigService {

	
	public BizConfigDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map) ;
	
	public List getTables(Map map);
	
	public void doPublish(BizConfigDomain vo);
	
	/*
	 *  1、判断flowId与TableName一致否...
	 *  2、更新isLast字段
	 */
	@Transactional
	public void bindingTable(BizConfigDomain vo);
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map);
	
	public BizConfigDomain insertObject(BizConfigDomain vo);

	public Integer updateObject(BizConfigDomain vo) ;
	
	public Integer deleteObjectById(Integer i) ;
	
}