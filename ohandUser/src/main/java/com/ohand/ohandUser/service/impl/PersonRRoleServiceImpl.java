package com.ohand.ohandUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.domain.PersonRRoleDomain;
import com.ohand.ohandUser.framework.SpringContextUtils;
import com.ohand.ohandUser.mapper.CommonMapper;
import com.ohand.ohandUser.mapper.PersonRRoleMapper;
import com.ohand.ohandUser.service.PersonRRoleService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;
@Service
public class PersonRRoleServiceImpl implements PersonRRoleService {
	
	@Autowired
	private  PersonRRoleMapper  personRRoleMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public PersonRRoleDomain getObjectVOById(Integer id) {
		return personRRoleMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return personRRoleMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return personRRoleMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=personRRoleMapper.getObjectMapListPagination(map);		 
		 List count = (personRRoleMapper.getObjectMapListCount(
					map));
			int numb=((Integer)count.get(0)).intValue();
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	@Override
	public PersonRRoleDomain insertObject(PersonRRoleDomain vo) {
		vo.setId(sequenceService.getNextId("pf_person_r_role"));
		personRRoleMapper.insertObject(vo);
		return vo;
	}

	@Override
	public Integer updateObject(PersonRRoleDomain vo) {
		return personRRoleMapper.updateObject(vo);
		}

	@Override
	public Integer deleteObjectById(Integer i) {
		return personRRoleMapper.deleteObjectById(i);
		}

	@Override
	public Integer deleteObjectByRoleId(Integer i) {
		return personRRoleMapper.deleteObjectByRoleId(i);
		}
	/*
	 * 一、先执行 删除操作，然后执行保存操作
	 * 二、1、保存记录到 pf_person_r_role 2、同时往PF_COMMON表插入记录 
	 */
	@Transactional
	public void saveList(PersonRRoleDomain personRRole, String[] commonIdArr, String roleCommonId) {
		CommonServiceImpl commonService=(CommonServiceImpl)SpringContextUtils.getBeanByClass(CommonServiceImpl.class);
		Map commonMap=new HashMap();
		commonMap.put("parentId", roleCommonId);
		commonMap.put("sourceType", CommonDomain.SOURCE_TYPE_PERSON+"");
		List oldList=commonMapper.getObjectVOList(commonMap);
		if(personRRole.getRoleId()!=null&&personRRole.getRoleId().intValue()>0){
			personRRoleMapper.deleteObjectByRoleId(personRRole.getRoleId());
			if(roleCommonId!=null&&!roleCommonId.equals("")&&!(roleCommonId.toLowerCase()).equals("null")&&!(roleCommonId.toLowerCase()).equals("undefined")){
				commonMapper.deleteObjectByParentIdAndSourceType(commonMap);
			}
		}
		
		if(commonIdArr.length>0){
			for(int i=0;i<commonIdArr.length;i++){
				String commonPersonId=commonIdArr[i];
				CommonDomain commonPerson=null;
				for(int j=0;j<oldList.size();j++){
					CommonDomain innerCommon=(CommonDomain)oldList.get(j);
					if(innerCommon.getId().intValue()==(new Integer(commonPersonId)).intValue()){
						commonPerson=innerCommon;
						break;
					}
				}
				if(commonPerson==null){
					commonPerson=commonMapper.getObjectVOById( new Integer(commonPersonId));
				}
				Integer thisCommonId=sequenceService.getNextId("pf_common");
				personRRole.setId(null);
				personRRole.setId(thisCommonId);
				personRRole.setPersonName(commonPerson.getName());
				personRRole.setPersonId(commonPerson.getRecordId());
				personRRoleMapper.insertObject(personRRole);
				
				CommonDomain common=new CommonDomain();
				common.setId(thisCommonId);
				common.setSourceType(new Integer(CommonDomain.SOURCE_TYPE_PERSON));
				common.setName(personRRole.getPersonName());
				common.setRecordId(personRRole.getPersonId());
				
				Integer commonId=commonService.findCommonIdByRoleId(personRRole.getRoleId());
				common.setParentId(commonId);
				common.setSysbol(new Integer(commonPersonId));
				commonMapper.insertObject(common);
			}
		}
	}

}
