package com.ohand.ohandUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.domain.PermissionItemDomain;
import com.ohand.ohandUser.domain.PermissionRelationDomain;
import com.ohand.ohandUser.mapper.CommonMapper;
import com.ohand.ohandUser.mapper.PermissionItemMapper;
import com.ohand.ohandUser.mapper.PermissionRelationMapper;
import com.ohand.ohandUser.service.PermissionRelationService;
import com.ohand.ohandUser.service.SequenceService;

@Service
public class PermissionRelationServiceImpl implements PermissionRelationService {

	@Autowired
	private  PermissionRelationMapper  permissionRelationMapper;
	@Autowired
	private PermissionItemMapper permissionItemMapper;
	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public PermissionRelationDomain getObjectVOById(Integer id) {
		return permissionRelationMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return permissionRelationMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return permissionRelationMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=permissionRelationMapper.getObjectMapListPagination(map);		 
		 List count=(permissionRelationMapper.getObjectMapListCount(map));
		 int numb=((Integer)((Map)count.get(0)).get("NUMB")).intValue();		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	@Override
	public PermissionRelationDomain insertObject(PermissionRelationDomain vo) {
		 permissionRelationMapper.insertObject(vo);
		 return vo;
	}

	/*
	 * 一、先执行 删除操作，
	 * 二、然后执行保存操作
	 */
	@Transactional
	public void saveList(PermissionRelationDomain permissionRelation, Integer[] commonIdsArr) {
		PermissionItemDomain permissionItemDomain=permissionItemMapper.getObjectVOById((permissionRelation.getPermissionItemId()));
		if(permissionRelation.getPermissionItemId()!=null&&permissionRelation.getPermissionItemId().intValue()>0){
			permissionRelationMapper.deleteObjectByPermissionItemId("pf_permission_relation",permissionRelation.getPermissionItemId());
		}
		if(commonIdsArr!=null&&commonIdsArr.length>0){
			Map commonMap=new HashMap();
			commonMap.put("commonIds", commonIdsArr);
			List commonList=commonMapper.getObjectVOList( commonMap);
			for(int i=0;i<commonList.size();i++){
				CommonDomain common=(CommonDomain)commonList.get(i);
				PermissionRelationDomain permissionRelationDomain=new PermissionRelationDomain();
				Integer id=sequenceService.getNextId("pf_permission_relation");
				permissionRelationDomain.setId(id);
				permissionRelationDomain.setPermissionItemId(permissionItemDomain.getId());
				permissionRelationDomain.setPermissionItemName(permissionItemDomain.getResName());
				permissionRelationDomain.setRecordId(common.getId());
				permissionRelationDomain.setRecordName(common.getName());
				permissionRelationDomain.setcType(common.getSourceType());
				permissionRelationMapper.insertObject(permissionRelationDomain);
			}
		}
	}

	@Override
	public Integer updateObject(PermissionRelationDomain vo) {
		return permissionRelationMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return permissionRelationMapper.deleteObjectById(i);
	}

}
