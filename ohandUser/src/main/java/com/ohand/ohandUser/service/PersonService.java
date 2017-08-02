package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.PersonDomain;

public interface PersonService {
	

	public PersonDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);
	
	public Integer updateObject(PersonDomain vo);
	
	public Integer deleteObjectById(Integer i);
	
	public void insertObjectCommon(PersonDomain person,String organizeId) ;
	
}
