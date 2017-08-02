package com.ohand.ohandFlow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowAttachFileDomain;
import com.ohand.ohandFlow.mapper.FlowAttachFileMapper;
import com.ohand.ohandFlow.service.FlowAttachFileService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FlowAttachFileServiceImpl implements FlowAttachFileService  {
	
	@Autowired
	private FlowAttachFileMapper flowAttachFileMapper;
	
	@Autowired
	private SequenceService  sequenceService;
	
	
	public FlowAttachFileDomain getObjectVOById(Integer id) {
		return flowAttachFileMapper.getObjectVOById(id);
	}
	public List getObjectMapList(Map map) {
		return flowAttachFileMapper.getObjectMapList(map);
	}
	public List getObjectVOList(Map map) {
		return flowAttachFileMapper.getObjectVOList(map);
	}
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=flowAttachFileMapper.getObjectMapListPagination(map);		 
		 List count=(flowAttachFileMapper.getObjectMapListCount(map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	public FlowAttachFileDomain insertObject(FlowAttachFileDomain vo) {
		vo.setId(sequenceService.getNextId("pf_flow_attachfile"));
		flowAttachFileMapper.insertObject(vo);
		return vo;
	}

	public Integer updateObject(FlowAttachFileDomain vo) {
		return flowAttachFileMapper.updateObject(vo);
	}

	public Integer deleteObjectById(Integer i) {
		return flowAttachFileMapper.deleteObjectById(i);
	}
	
}