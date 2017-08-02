package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.PersonRRoleDomain;

public interface PersonRRoleService  {
	
	public PersonRRoleDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);
	
	public PersonRRoleDomain insertObject(PersonRRoleDomain vo);
	
	public Integer updateObject(PersonRRoleDomain vo) ;
	
	public Integer deleteObjectById(Integer i) ;
	
	public Integer deleteObjectByRoleId(Integer i) ;

	public void saveList(PersonRRoleDomain personRRole,String[] commonIdArr,String roleCommonId);
	
}