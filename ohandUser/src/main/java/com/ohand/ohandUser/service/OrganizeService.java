package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.OrganizeDomain;

public interface OrganizeService  {
	
	public OrganizeDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);
	
	public void saveObjectCommonDomain(OrganizeDomain vo);
	
	public Integer deleteObjectById(Integer i) ;
	
}