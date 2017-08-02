
package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;

public interface CommonService  {
	
	public CommonDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);
	
	public CommonDomain insertObject(CommonDomain vo);
	
	public Integer updateObject(CommonDomain vo);
	
	public Integer updateObjectName(Map map);
	
	public Integer deleteObjectById(Integer i);
	
	public Integer findCommonIdByOrgId(Integer orgId);
	
	public Integer findCommonIdByGroupId(Integer groupId);
	
	public Integer findCommonIdByRoleId(Integer roleId);
	
	public Integer findCommonIdByPersonId(Integer personId);
	
	public List hasChild(Map map);
	
	public List filterAddressResult(String topIds, List noPersonList,List commonList);
	
	public boolean isExistTopIds(String topIds,List noPersonList,CommonDomain c);
	
	public boolean judgetParentDomainInTopIds(List noPersonList,Integer parentId,String[] topIdArr);
	
}