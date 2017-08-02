package com.ohand.ohandFlow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowActivityDomain;
import com.ohand.ohandFlow.mapper.FlowActivityMapper;
import com.ohand.ohandFlow.service.FlowActivityService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FlowActivityServiceImpl  implements  FlowActivityService {

	@Autowired
	private FlowActivityMapper flowActivityMapper;
	@Autowired
	private SequenceService  sequenceService;
	
	public FlowActivityDomain getObjectVOById(Integer id) {
		return flowActivityMapper.getObjectVOById( id);
	}

	public List getObjectMapList(Map map) {
		return flowActivityMapper.getObjectMapList( map);
	}

	public List getObjectVOList(Map map) {
		return flowActivityMapper.getObjectVOList( map);
	}

	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = flowActivityMapper.getObjectMapListPagination(
				 map);
		List count = (flowActivityMapper.getObjectMapListCount(
				map));
		int numb=((Integer)count.get(0)).intValue();
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	public FlowActivityDomain insertObject(FlowActivityDomain vo) {
		vo.setId(sequenceService.getNextId("pf_flow_activity"));
		flowActivityMapper.insertObject( vo);
		return vo;
	}

	public Integer updateObject(FlowActivityDomain vo) {
		return flowActivityMapper.updateObject( vo);
	}

	public Integer deleteObjectById(Integer i) {
		return flowActivityMapper.deleteObjectById( i);
	}

}
