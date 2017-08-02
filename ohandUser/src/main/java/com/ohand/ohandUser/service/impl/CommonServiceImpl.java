package com.ohand.ohandUser.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.mapper.CommonMapper;
import com.ohand.ohandUser.service.CommonService;
import com.ohand.ohandUser.service.SequenceService;
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	public CommonDomain getObjectVOById(Integer id) {
		return commonMapper.getObjectVOById(id);
	}

	public List getObjectMapList(Map map) {
		return commonMapper.getObjectMapList(map);
	}

	public List getObjectVOList(Map map) {
		return commonMapper.getObjectVOList(map);
	}

	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=commonMapper.getObjectMapListPagination(map);		 
		 List count = (commonMapper.getObjectMapListCount(map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	public CommonDomain insertObject(CommonDomain vo) {
		vo.setId(sequenceService.getNextId("pf_common"));
		commonMapper.insertObject(vo);
		return vo;
	}

	public Integer updateObject(CommonDomain vo) {
		return commonMapper.updateObject(vo);
	}

	public Integer updateObjectName(Map map) {
		return commonMapper.updateObjectName(map);
	}

	public Integer deleteObjectById(Integer i) {
		return commonMapper.deleteObjectById(i);
	}

	@Override
	public Integer findCommonIdByOrgId(Integer orgId) {
		return orgId;
	}

	public Integer findCommonIdByGroupId(Integer groupId) {
		return groupId;
	}

	public Integer findCommonIdByRoleId(Integer roleId) {
		return roleId;
	}

	public Integer findCommonIdByPersonId(Integer personId) {
		return personId;
	}

	public List hasChild(Map map) {
		return commonMapper.getObjectMapListCount(map);
		}

	public List filterAddressResult(String topIds, List noPersonList, List commonList) {
		List retList=new ArrayList();
		for(int i=(commonList.size()-1);i>=0;i--){
			CommonDomain c=(CommonDomain) commonList.get(i);
			boolean isExist=isExistTopIds( topIds,  noPersonList, c);
			if(isExist==true){
				retList.add(c);
			}
		}
		return retList;
	}

	public boolean isExistTopIds(String topIds, List noPersonList, CommonDomain c) {
		boolean isExist=false;
		String[] topIdArr=topIds.split(",");
		int i=0;
		while(i<=20){
			i++;
			isExist=judgetParentDomainInTopIds(noPersonList,c.getParentId(),topIdArr);
			if(isExist==true){
				return isExist;
			}
			// 寻找下一个parentDomian
			boolean hasParent=false;
			for(int j=0;j<noPersonList.size();j++){
				CommonDomain cc=(CommonDomain)noPersonList.get(j);
				if(cc.getId().intValue()==c.getParentId().intValue()){
					hasParent=true;
					c=cc;
					break;
				}
			}
			if(hasParent==false){
				break;
			}
		}
		return isExist;
	}

	public boolean judgetParentDomainInTopIds(List noPersonList, Integer parentId, String[] topIdArr) {
		for(int i=0;i<noPersonList.size();i++){
			CommonDomain c=(CommonDomain)noPersonList.get(i);
			if(c.getId().intValue()==parentId.intValue()){
				for(int j=0;j<topIdArr.length;j++){
					if(topIdArr[j].equals(""+parentId)){
						return true;
					}
				}
			}
		}
		return false;
	}

}
