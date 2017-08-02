package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.ScheduleInfoDomain;

public interface ScheduleInfoService {
	
	public ScheduleInfoDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map) ;
	
	public Pagination getObjectVOListPage(Map map);

	public ScheduleInfoDomain insertObject(ScheduleInfoDomain vo);
	
	public Integer updateObject(ScheduleInfoDomain vo);
	
	public Integer deleteObjectById(Integer i);
	
}