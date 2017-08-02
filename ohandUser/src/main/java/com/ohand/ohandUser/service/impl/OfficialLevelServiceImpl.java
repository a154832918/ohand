package com.ohand.ohandUser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.OfficialLevelDomain;
import com.ohand.ohandUser.mapper.OfficialLevelMapper;
import com.ohand.ohandUser.service.OfficialLevelService;

@Service
public class OfficialLevelServiceImpl implements OfficialLevelService {

	@Autowired
	private OfficialLevelMapper officialLevelMapper;
	
	@Override
	public OfficialLevelDomain getObjectVOById(Integer id) {
		return officialLevelMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return officialLevelMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return officialLevelMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=officialLevelMapper.getObjectMapListPagination(map);		 
		 List count=(officialLevelMapper.getObjectMapListCount(map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public OfficialLevelDomain insertObject(OfficialLevelDomain vo) {
		officialLevelMapper.insertObject(vo);
		return vo;
	}

	@Override
	public Integer updateObject(OfficialLevelDomain vo) {
		return officialLevelMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return officialLevelMapper.deleteObjectById(i);
	}

}
