package com.ohand.ohandFlow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.EventListenConfigDomain;
import com.ohand.ohandFlow.mapper.EventListenConfigMapper;
import com.ohand.ohandFlow.service.EventListenConfigService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class EventListenConfigServiceImpl implements EventListenConfigService  {
	
	@Autowired
	private EventListenConfigMapper eventListenConfigMapper;
	@Autowired
	private SequenceService  sequenceService;
	
	public EventListenConfigDomain getObjectVOById(Integer id) {
		return eventListenConfigMapper.getObjectVOById(id);
	}
	
	public List getObjectMapList(Map map) {
		return eventListenConfigMapper.getObjectMapList(map);
	}
	
	public List getObjectVOList(Map map) {
		return eventListenConfigMapper.getObjectVOList(map);
	}
	
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
			List retList = eventListenConfigMapper.getObjectMapListPagination(
					 map);
			List count = (eventListenConfigMapper.getObjectMapListCount(
					 map));
			int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB")); 
		return new Pagination(pageIndex,pageSize,numb,retList);
	}
	
	public EventListenConfigDomain insertObject(EventListenConfigDomain vo) {
		vo.setId(sequenceService.getNextId("pf_event_listen_config"));
		eventListenConfigMapper.insertObject(vo);
		return vo;
	}
	
	public Integer updateObject(EventListenConfigDomain vo) {
		return eventListenConfigMapper.updateObject(vo);
	}
	
	public Integer deleteObjectById(Integer i) {
		return eventListenConfigMapper.deleteObjectById(i);
	}
	
}