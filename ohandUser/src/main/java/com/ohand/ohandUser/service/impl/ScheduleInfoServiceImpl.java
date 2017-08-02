package com.ohand.ohandUser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.ScheduleInfoDomain;
import com.ohand.ohandUser.mapper.ScheduleInfoMapper;
import com.ohand.ohandUser.service.ScheduleInfoService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;
@Service
public class ScheduleInfoServiceImpl implements ScheduleInfoService {
	
	@Autowired
	private ScheduleInfoMapper scheduleInfoMapper;
	
	@Autowired
	private  SequenceService  sequenceService;	
	
	@Override
	public ScheduleInfoDomain getObjectVOById(Integer id) {
		return scheduleInfoMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return scheduleInfoMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return scheduleInfoMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=scheduleInfoMapper.getObjectVOList(map);		 
		 List count=(scheduleInfoMapper.getObjectMapListCount(map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	@Override
	public ScheduleInfoDomain insertObject(ScheduleInfoDomain vo) {
		vo.setId(sequenceService.getNextId("pf_schedule_info"));
		scheduleInfoMapper.insertObject(vo);
		return vo;
	}

	@Override
	public Integer updateObject(ScheduleInfoDomain vo) {
		return scheduleInfoMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return scheduleInfoMapper.deleteObjectById(i);
	}

}
