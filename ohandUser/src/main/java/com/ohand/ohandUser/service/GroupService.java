package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.GroupDomain;

public interface GroupService {
	
	public GroupDomain getObjectVOById(Integer id);

	public List getObjectMapList(Map map);

	public List getObjectVOList(Map map);

	public Pagination getObjectVOListPage(Map map);

	public Integer updateObject(GroupDomain vo);

	public Integer deleteObjectById(Integer i);

	public void insertObjectCommon(GroupDomain vo);
	
}
