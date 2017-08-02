package com.ohand.ohandFlow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowConnectorDomain;
import com.ohand.ohandFlow.mapper.FlowConnectorMapper;
import com.ohand.ohandFlow.service.FlowConnectorService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FlowConnectorServiceImpl   implements  FlowConnectorService {

	@Autowired
	private FlowConnectorMapper flowConnectorMapper;
	@Autowired
	private SequenceService  sequenceService;

	public FlowConnectorDomain getObjectVOById(Integer id) {
		return flowConnectorMapper.getObjectVOById( id);
	}

	public List getObjectMapList(Map map) {
		return flowConnectorMapper.getObjectMapList( map);
	}

	public List getObjectVOList(Map map) {
		return flowConnectorMapper.getObjectVOList( map);
	}

	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = flowConnectorMapper.getObjectMapListPagination(
				 map);
		List count = (flowConnectorMapper.getObjectMapListCount(
				 map));
		int numb=((Integer)count.get(0)).intValue();
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	public FlowConnectorDomain insertObject(FlowConnectorDomain vo) {
		vo.setId(sequenceService.getNextId("t_pmflow_connector"));
		flowConnectorMapper.insertObject( vo);
		return vo;
	}

	public Integer updateObject(FlowConnectorDomain vo) {
		return flowConnectorMapper.updateObject( vo);
	}

	public Integer deleteObjectById(Integer i) {
		return flowConnectorMapper.deleteObjectById( i);
	}

}