package com.ohand.ohandUser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.PermissionItemDomain;
import com.ohand.ohandUser.mapper.PermissionItemMapper;
import com.ohand.ohandUser.service.PermissionItemService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;

@Service
public class PermissionItemServiceImpl implements PermissionItemService {

	@Autowired
	private PermissionItemMapper permissionItemMapper;
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public PermissionItemDomain getObjectVOById(Integer id) {
		return permissionItemMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return permissionItemMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return permissionItemMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=permissionItemMapper.getObjectMapListPagination(map);		 
		 List count=(permissionItemMapper.getObjectMapListCount(map));
			int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
			return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public Pagination getObjectVOListTreePage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=permissionItemMapper.getObjectVOListTreePagination(map);		 
		 List count=(permissionItemMapper.getObjectVOListTreeCount(map));
		 int numb=((Integer)(count.get(0))).intValue();
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	@Override
	public PermissionItemDomain insertObject(PermissionItemDomain vo) {
		 vo.setId(new Integer(sequenceService.getNextId("pf_permission_item")));
		 if (vo.getParentId() != null && vo.getParentId().intValue() == 0) {
			 
		 } else {
			 
		 }
		 permissionItemMapper.insertObject(vo);
		 return vo;
	}

	@Override
	public Integer updateObject(PermissionItemDomain vo) {
		return permissionItemMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return permissionItemMapper.deleteObjectById(i);
	}

	@Override
	public List hasChild(Map map) {
		return permissionItemMapper.getObjectMapListCount(map);
	}

}
