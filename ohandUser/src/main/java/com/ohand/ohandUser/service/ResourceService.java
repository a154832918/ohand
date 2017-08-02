package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.ResourceDomain;

public interface ResourceService {

	public ResourceDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map) ;

	public Pagination getObjectVOListPage(Map map);

	public ResourceDomain insertObject(ResourceDomain vo) ;

	public Integer updateObject(ResourceDomain vo);

	public Integer deleteObjectById(Integer i);

	public List fetchSortedMenuByTopId(String topId);
	
}
