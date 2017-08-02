package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.OrganizeRPersonDomain;

public interface OrganizeRPersonService {
	
	public OrganizeRPersonDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map);

	public Integer deleteObjectByPersonId(Integer personId);
	
	public Integer deleteObjectByOrganizeId(Integer organizeId);
	
	public void saveList(OrganizeRPersonDomain organizeRPerson,String[] orgIdArr);
	
	public Integer updateObject(OrganizeRPersonDomain vo);
	
	public Integer deleteObjectById(Integer i) ;
	
	
}