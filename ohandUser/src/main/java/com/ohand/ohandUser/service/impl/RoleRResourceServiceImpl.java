package com.ohand.ohandUser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ohand.ohandUser.common.JsonUtil;
import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.PermissionItemDomain;
import com.ohand.ohandUser.domain.ResourceDomain;
import com.ohand.ohandUser.domain.RoleRResourceDomain;
import com.ohand.ohandUser.mapper.PermissionItemMapper;
import com.ohand.ohandUser.mapper.ResourceMapper;
import com.ohand.ohandUser.mapper.RoleRResourceMapper;
import com.ohand.ohandUser.service.RoleRResourceService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;

@Service
public class RoleRResourceServiceImpl implements RoleRResourceService {

	@Autowired
	private RoleRResourceMapper roleRResourceMapper;
	@Autowired
	private PermissionItemMapper permissionItemMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public RoleRResourceDomain getObjectVOById(Integer id) {
		return roleRResourceMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return roleRResourceMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return roleRResourceMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = roleRResourceMapper.getObjectMapListPagination(map);
		List count = (roleRResourceMapper.getObjectMapListCount(map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public RoleRResourceDomain insertObject(RoleRResourceDomain vo) {
		vo.setId(sequenceService.getNextId("pf_role_r_resource"));
		roleRResourceMapper.insertObject(vo);
		return vo;
	}

	@Override
	public void save(String resourceIds, RoleRResourceDomain roleRResource) {
		if(resourceIds!=null && !resourceIds.equals("")){
			JSONObject jsonObj=JsonUtil.parseStr2JsonObj(resourceIds);	
			JSONArray jsonArray=(JSONArray) jsonObj.get("rows");
			for(int i=0;i<jsonArray.size();i++){
				Object item=jsonArray.get(i);
				if(item!=null){
					JSONObject itemObj=(JSONObject)item;
					String id=""+itemObj.get("ID");
					if(id!=null&&!id.equals("")&&!(id.toLowerCase()).equals("null")){
						continue;
					}
					roleRResource.setId(null);
					roleRResource.setResourceId(((Integer) itemObj.get("RESOURCE_ID")));
					String sourceType=itemObj.get("RESOURCE_TYPE")+"";
					if(sourceType.equals(RoleRResourceDomain.RESOURCE_TYPE_Menu)){
						ResourceDomain resourceDomain=resourceMapper.getObjectVOById( ((Integer) itemObj.get("RESOURCE_ID")));
						roleRResource.setResourceName(resourceDomain.getName());
						roleRResource.setResourceType(new Integer(RoleRResourceDomain.RESOURCE_TYPE_Menu));
						this.insertObject(roleRResource);
					}else{
						PermissionItemDomain permissionItemDomain=permissionItemMapper.getObjectVOById(((Integer) itemObj.get("RESOURCE_ID")));
						roleRResource.setResourceName(permissionItemDomain.getResName());
						roleRResource.setResourceType(new Integer(RoleRResourceDomain.RESOURCE_TYPE_PermissionItem));
						this.insertObject(roleRResource);
					}
				}
			}	
		}
	}

	@Override
	public Integer updateObject(RoleRResourceDomain vo) {
		return roleRResourceMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return roleRResourceMapper.deleteObjectById(i);
	}

}
