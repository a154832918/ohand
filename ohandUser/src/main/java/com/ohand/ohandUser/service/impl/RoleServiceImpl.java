package com.ohand.ohandUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.domain.RoleDomain;
import com.ohand.ohandUser.framework.SpringContextUtils;
import com.ohand.ohandUser.mapper.CommonMapper;
import com.ohand.ohandUser.mapper.RoleMapper;
import com.ohand.ohandUser.service.RoleService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private CommonMapper commonMapper;	
	
	@Autowired
	private  SequenceService  sequenceService;
	
	
	@Override
	public RoleDomain getObjectVOById(Integer id) {
		return roleMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return roleMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return roleMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = roleMapper.getObjectMapListPagination(map);
		List count = (roleMapper.getObjectMapListCount(map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public Integer updateObject(RoleDomain vo) {
		return roleMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		
		PersonRRoleServiceImpl personRRoleService =(PersonRRoleServiceImpl)SpringContextUtils.getBeanByClass(PersonRRoleServiceImpl.class);
		personRRoleService.deleteObjectByRoleId(i);
		
		Map cMap=new HashMap();
		cMap.put("sourceType", CommonDomain.SOURCE_TYPE_ROLE);
		cMap.put("recordId", i);
		commonMapper.deleteObjectByRecordId(cMap);
		
		
		Map rMap=new HashMap();
		rMap.put("sourceType", CommonDomain.SOURCE_TYPE_PERSON);
		rMap.put("parentId", i);
		commonMapper.deleteObjectByParentIdAndSourceType( rMap);
		
		return roleMapper.deleteObjectById(i);
		
	}

	@Override
	public RoleDomain insertObjectCommon(RoleDomain vo) {
		Integer thisCommonId=sequenceService.getNextId("pf_common");
		vo.setId(thisCommonId);
		roleMapper.insertObject(vo);
		
		CommonDomain common=new CommonDomain();
		common.setId(thisCommonId);
		common.setSourceType(CommonDomain.SOURCE_TYPE_ROLE);
		common.setRecordId(vo.getId());
		common.setParentId(new Integer(0));
		common.setName(vo.getName());
		common.setSysbol(common.getId());
		commonMapper.insertObject(common);
		return vo;
	}

}
