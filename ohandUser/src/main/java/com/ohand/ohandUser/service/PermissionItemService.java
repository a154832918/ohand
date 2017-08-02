package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.PermissionItemDomain;

public interface PermissionItemService  {
	
	public PermissionItemDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map)  ;
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map) ;
	
	public Pagination getObjectVOListTreePage(Map map) ;
	
	public PermissionItemDomain insertObject(PermissionItemDomain vo)  ;
	
	public Integer updateObject(PermissionItemDomain vo) ;
	
	public Integer deleteObjectById(Integer i)  ;
	
	public List hasChild(Map map) ;
	
}