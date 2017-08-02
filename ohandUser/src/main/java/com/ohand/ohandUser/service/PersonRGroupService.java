package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.PersonRGroupDomain;

public interface PersonRGroupService  {
	
	public PersonRGroupDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);
	
	public void saveList(PersonRGroupDomain personRGroup,String[] commonIdArr);
	
	public Integer updateObject(PersonRGroupDomain vo) ;
	
	public Integer deleteObjectById(Integer i) ;
	
	public Integer deleteObjectByGroupId(Integer i);
	
}