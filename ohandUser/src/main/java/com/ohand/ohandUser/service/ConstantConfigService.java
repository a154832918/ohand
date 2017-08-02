package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.ConstantConfigDomain;

public interface ConstantConfigService {

	public ConstantConfigDomain getObjectVOById(Integer id) ;

	public List getObjectMapList(Map map) ;

	public List getObjectVOList(Map map) ;

	public Pagination getObjectVOListPage(Map map) ;

	public Pagination getObjectVOListTreePage(Map map)  ;
	
	public ConstantConfigDomain insertObject(ConstantConfigDomain vo) ;

	public Integer updateObject(ConstantConfigDomain vo)  ;

	public Integer deleteObjectById(Integer i)  ;

	public List hasChild(Map map) ;
	
}