package com.ohand.spring01.mapper;

import java.util.List;
import java.util.Map;

import com.ohand.spring01.common.Pagination;

public interface BaseMapper<T> {
	
	public T getObjectVOById(Object id) ;
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Integer updateObject(T vo);
	
	public void insertObject(T vo);
	
	public Integer deleteObjectById(Integer i);
	
}


