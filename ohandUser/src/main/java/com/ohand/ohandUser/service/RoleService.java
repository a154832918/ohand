package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.RoleDomain;

public interface RoleService {
	
	public RoleDomain getObjectVOById(Integer id);

	public List getObjectMapList(Map map) ;

	public List getObjectVOList(Map map);

	public Pagination getObjectVOListPage(Map map);

	public Integer updateObject(RoleDomain vo);

	public Integer deleteObjectById(Integer i);

	public RoleDomain insertObjectCommon(RoleDomain vo);
	
}
