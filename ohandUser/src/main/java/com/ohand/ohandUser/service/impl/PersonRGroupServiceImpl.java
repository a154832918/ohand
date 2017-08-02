package com.ohand.ohandUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.domain.PersonRGroupDomain;
import com.ohand.ohandUser.framework.SpringContextUtils;
import com.ohand.ohandUser.mapper.CommonMapper;
import com.ohand.ohandUser.mapper.PersonMapper;
import com.ohand.ohandUser.mapper.PersonRGroupMapper;
import com.ohand.ohandUser.service.PersonRGroupService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;

@Service
public class PersonRGroupServiceImpl implements PersonRGroupService {

	@Autowired
	private PersonRGroupMapper personRGroupMapper;
	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public PersonRGroupDomain getObjectVOById(Integer id) {
		return personRGroupMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return personRGroupMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return personRGroupMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=personRGroupMapper.getObjectMapListPagination(map);	
		 List count = (personRGroupMapper.getObjectMapListCount(map));
		 int numb=((Integer)((Map)count.get(0)).get("NUMB")).intValue();		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	/*
	 * 一、先执行 删除操作，然后执行保存操作
	 * 二、1、保存记录到 pf_person_r_group 2、同时往PF_COMMON表插入记录 
	 */
	@Transactional
	public void saveList(PersonRGroupDomain personRGroup, String[] commonIdArr) {
		CommonServiceImpl commonService=(CommonServiceImpl)SpringContextUtils.getBeanByClass(CommonServiceImpl.class);
		if(personRGroup.getGroupId()!=null&&personRGroup.getGroupId().intValue()>0){
			
			personRGroupMapper.deleteObjectByGroupId(personRGroup.getGroupId());
		
			Map delPersonMap=new HashMap();
				delPersonMap.put("sourceType", CommonDomain.SOURCE_TYPE_GROUP);
				delPersonMap.put("recordId", personRGroup.getGroupId());
			List groupList=commonMapper.getObjectVOList(delPersonMap);
			CommonDomain groupCommon=(CommonDomain)groupList.get(0);
			Map commonMap=new HashMap();
			commonMap.put("parentId", groupCommon.getId());
			commonMap.put("sourceType", CommonDomain.SOURCE_TYPE_PERSON+"");
			commonMapper.deleteObjectByParentIdAndSourceType(commonMap);
		}
		
		if(commonIdArr!=null&&commonIdArr.length>0){
			for(int i=0;i<commonIdArr.length;i++){
				Integer thisCommonId=sequenceService.getNextId("pf_common");
				String commonPersonId=commonIdArr[i];
				CommonDomain commonPerson=commonMapper.getObjectVOById(new Integer(commonPersonId));
				personRGroup.setId(null);
				personRGroup.setId(thisCommonId);
				personRGroup.setPersonName(commonPerson.getName());
				personRGroup.setPersonId(commonPerson.getRecordId());
				personRGroupMapper.insertObject(personRGroup);
				
				CommonDomain common=new CommonDomain();
				common.setId(thisCommonId);
				common.setSourceType(new Integer(CommonDomain.SOURCE_TYPE_PERSON));
				common.setName(personRGroup.getPersonName());
				common.setRecordId(personRGroup.getPersonId());
				
				Integer commonId=commonService.findCommonIdByGroupId(personRGroup.getGroupId());
				common.setParentId(commonId);
				common.setSysbol(new Integer(commonPersonId));
				commonMapper.insertObject(common);
			}
		}
	}

	@Override
	public Integer updateObject(PersonRGroupDomain vo) {
		return personRGroupMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return personRGroupMapper.deleteObjectById(i);
	}

	@Override
	public Integer deleteObjectByGroupId(Integer i) {
		return personRGroupMapper.deleteObjectByGroupId(i);
	}

}
