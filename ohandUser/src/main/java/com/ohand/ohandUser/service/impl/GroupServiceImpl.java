package com.ohand.ohandUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.domain.GroupDomain;
import com.ohand.ohandUser.framework.SpringContextUtils;
import com.ohand.ohandUser.mapper.CommonMapper;
import com.ohand.ohandUser.mapper.GroupMapper;
import com.ohand.ohandUser.service.GroupService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;
@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupMapper groupMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public GroupDomain getObjectVOById(Integer id) {
		return groupMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return groupMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return groupMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = groupMapper.getObjectMapListPagination(map);
		List count = (groupMapper.getObjectMapListCount(map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public Integer updateObject(GroupDomain vo) {
		return groupMapper.updateObject(vo);
	}

	@Transactional
	public Integer deleteObjectById(Integer i) {
		
		PersonRGroupServiceImpl personRGroupService =(PersonRGroupServiceImpl)SpringContextUtils.getBeanByClass(PersonRGroupServiceImpl.class);
		personRGroupService.deleteObjectByGroupId(i);
		
		Map cMap=new HashMap();
		cMap.put("sourceType", CommonDomain.SOURCE_TYPE_GROUP);
		cMap.put("recordId", i);
		commonMapper.deleteObjectByRecordId(cMap);
		
		Map rMap=new HashMap();
		rMap.put("sourceType", CommonDomain.SOURCE_TYPE_PERSON);
		rMap.put("parentId", i);
		commonMapper.deleteObjectByParentIdAndSourceType( rMap);
		
		return groupMapper.deleteObjectById(i);
		
	}

	@Override
	public void insertObjectCommon(GroupDomain vo) {
		
		Integer thisCommonId=sequenceService.getNextId("pf_common");
			vo.setId(thisCommonId);
		groupMapper.insertObject(vo);
		
		CommonDomain common=new CommonDomain();
			common.setId(thisCommonId);
			common.setSourceType(CommonDomain.SOURCE_TYPE_GROUP);
			common.setRecordId(vo.getId());
			common.setParentId(new Integer(0));
			common.setName(vo.getName());
			common.setSysbol(common.getId());
		commonMapper.insertObject( common);
			
	}

}
