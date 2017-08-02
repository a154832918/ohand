package com.ohand.ohandUser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.ConstantConfigDomain;
import com.ohand.ohandUser.mapper.ConstantConfigMapper;
import com.ohand.ohandUser.service.ConstantConfigService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;
@Service
public class ConstantConfigServiceImpl implements ConstantConfigService {

	@Autowired
	private ConstantConfigMapper  constantConfigMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public ConstantConfigDomain getObjectVOById(Integer id) {
		return constantConfigMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return constantConfigMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return constantConfigMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = constantConfigMapper.getObjectMapListPagination(map);
		List count = (constantConfigMapper.getObjectMapListCount( map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public Pagination getObjectVOListTreePage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=constantConfigMapper.getObjectVOListTreePagination(map);		 
		 List count=(constantConfigMapper.getObjectVOListTreeCount(map));
		 int numb=((Integer)(count.get(0))).intValue();
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	@Override
	public ConstantConfigDomain insertObject(ConstantConfigDomain vo) {
		vo.setId(sequenceService.getNextId("pf_constant_config"));
		if (vo.getParentId() != null && vo.getParentId().intValue() == 0) {
			vo.setTopId(vo.getId());
			vo.setParentNodes(vo.getId()+",");
		} else {
			ConstantConfigDomain parentVO = getObjectVOById(vo.getParentId());
			vo.setTopId(parentVO.getTopId());
			vo.setParentNodes(parentVO.getParentNodes()+vo.getId()+",");
		}
		constantConfigMapper.insertObject(vo);
		return vo;
	}

	@Override
	public Integer updateObject(ConstantConfigDomain vo) {
		return constantConfigMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return constantConfigMapper.deleteObjectById(i);
	}

	@Override
	public List hasChild(Map map) {
		return constantConfigMapper.getObjectMapListCount(map);
	}

}
