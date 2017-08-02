package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.UserDomain;

public interface UserService  {

	public UserDomain getObjectVOById(Integer id) ;
	
	public UserDomain getUserByAccount(String userName) ;
	
	public UserDomain findUserByThirdId(String thirdId) ;
	
	public List getObjectMapList(Map map)  ;
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map)  ;

	public UserDomain insertObject(UserDomain vo) ;

	public Integer updateObject(UserDomain vo)  ;

	public Integer deleteObjectById(Integer i) ;
	
}