
package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.OfficialLevelDomain;

public interface OfficialLevelService{
	
	public OfficialLevelDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);

	public OfficialLevelDomain insertObject(OfficialLevelDomain vo);

	public Integer updateObject(OfficialLevelDomain vo);
	
	public Integer deleteObjectById(Integer i);

}