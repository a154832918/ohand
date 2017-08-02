package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.PermissionRelationDomain;

public interface PermissionRelationService  {

	public PermissionRelationDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map) ;
	
	public PermissionRelationDomain insertObject(PermissionRelationDomain vo);
	
	public void saveList(PermissionRelationDomain permissionRelation,Integer[] commonIdsArr);
	
	public Integer updateObject(PermissionRelationDomain vo);
	
	public Integer deleteObjectById(Integer i);
	
}

