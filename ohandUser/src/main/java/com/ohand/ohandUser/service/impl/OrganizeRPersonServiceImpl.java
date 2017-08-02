package com.ohand.ohandUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.domain.OrganizeRPersonDomain;
import com.ohand.ohandUser.framework.SpringContextUtils;
import com.ohand.ohandUser.mapper.CommonMapper;
import com.ohand.ohandUser.mapper.OrganizeRPersonMapper;
import com.ohand.ohandUser.service.OrganizeRPersonService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;

@Service
public class OrganizeRPersonServiceImpl implements OrganizeRPersonService {
	
	@Autowired
	private OrganizeRPersonMapper  organizeRPersonMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public OrganizeRPersonDomain getObjectVOById(Integer id) {
		return organizeRPersonMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return organizeRPersonMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return organizeRPersonMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=organizeRPersonMapper.getObjectMapListPagination(map);	
		 List count = (organizeRPersonMapper.getObjectMapListCount(map));
		 int numb=new Integer((""+((Map)count.get(0)).get("NUMB"))).intValue();		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	@Override
	public Integer deleteObjectByPersonId(Integer personId) {
		return organizeRPersonMapper.deleteObjectByPersonId(personId);
		}

	@Override
	public Integer deleteObjectByOrganizeId(Integer organizeId) {
		return organizeRPersonMapper.deleteObjectByOrganizeId(organizeId);
	}

	/*
	 * 一、先执行 删除操作，然后执行保存操作
	 * 二、1、保存记录到 pf_organize_r_person 2、同时往PF_COMMON表插入记录 
	 */
	@Transactional
	public void saveList(OrganizeRPersonDomain organizeRPerson, String[] orgIdArr) {
		CommonServiceImpl commonService=(CommonServiceImpl) SpringContextUtils.getBeanByClass(CommonServiceImpl.class);
		organizeRPersonMapper.deleteObjectByPersonId(organizeRPerson.getPersonId());
		
		Map commonMap=new HashMap();
		commonMap.put("recordId", organizeRPerson.getPersonId());
		commonMap.put("sourceType", CommonDomain.SOURCE_TYPE_PERSON+"");
		
		if(orgIdArr.length>0){
			for(int i=0;i<orgIdArr.length;i++){
				String commonId=orgIdArr[i];
				Integer thisCommonId=sequenceService.getNextId("pf_common");
				commonMap.put("parentId", commonId);
				commonMapper.deleteObjectByRecordIdAndParentId(commonMap);
				
				CommonDomain commonDomain=commonService.getObjectVOById(new Integer(commonId));
				
				organizeRPerson.setId(null);
				organizeRPerson.setId(thisCommonId);
				organizeRPerson.setOrganizeName(commonDomain.getName());
				organizeRPerson.setOrganizeId(commonDomain.getRecordId());
				organizeRPersonMapper.insertObject(organizeRPerson);
				
				CommonDomain common=new CommonDomain();
				common.setId(thisCommonId);
				common.setSourceType(new Integer(CommonDomain.SOURCE_TYPE_PERSON));
				common.setName(organizeRPerson.getPersonName());
				common.setRecordId(organizeRPerson.getPersonId());
				
				common.setParentId(new Integer(commonId));
				common.setSysbol(commonService.findCommonIdByPersonId(organizeRPerson.getPersonId()));
				commonMapper.insertObject(common);
				
			}
		}
	}

	@Override
	public Integer updateObject(OrganizeRPersonDomain vo) {
		return  organizeRPersonMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return organizeRPersonMapper.deleteObjectById(i);
	}

}
