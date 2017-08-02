package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.RoleRResourceDomain;

public interface RoleRResourceService  {

	public RoleRResourceDomain getObjectVOById(Integer id) ;

	public List getObjectMapList(Map map) ;

	public List getObjectVOList(Map map) ;

	public Pagination getObjectVOListPage(Map map) ;

	public RoleRResourceDomain insertObject(RoleRResourceDomain vo) ;

	public void save(String resourceIds, RoleRResourceDomain roleRResource) ;
	
	public Integer updateObject(RoleRResourceDomain vo)  ;
	
	public Integer deleteObjectById(Integer i) ;
	
}
