package com.ohand.ohandUser.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
	
	public T getObjectVOById(Object id) ;
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Integer updateObject(T vo);
	
	public void insertObject(T vo);
	
	public Integer deleteObjectById(Object i);
	
	public List getObjectMapListPagination(Map map);
	
	public List  getObjectMapListCount(Map map);
	
}


