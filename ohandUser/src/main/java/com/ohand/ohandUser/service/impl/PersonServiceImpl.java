package com.ohand.ohandUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.domain.OrganizeDomain;
import com.ohand.ohandUser.domain.OrganizeRPersonDomain;
import com.ohand.ohandUser.domain.PersonDomain;
import com.ohand.ohandUser.framework.SpringContextUtils;
import com.ohand.ohandUser.mapper.CommonMapper;
import com.ohand.ohandUser.mapper.OrganizeMapper;
import com.ohand.ohandUser.mapper.OrganizeRPersonMapper;
import com.ohand.ohandUser.mapper.PersonMapper;
import com.ohand.ohandUser.service.PersonService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private OrganizeRPersonMapper organizeRPersonMapper;
	
	@Autowired
	private OrganizeMapper organizeMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	public PersonDomain getObjectVOById(Integer id){
		return personMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return personMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return personMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=personMapper.getObjectMapListPagination(map);		 
		 List count = (personMapper.getObjectMapListCount(map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	@Override
	public Integer updateObject(PersonDomain vo) {
		return personMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return personMapper.deleteObjectById(i);
	}

	@Override
	public void insertObjectCommon(PersonDomain person, String organizeId) {
		if(person.getId()!=null){
			updateObject(person);
			Map map=new HashMap();
			map.put("recordId", person.getId());
			map.put("sourceType", CommonDomain.SOURCE_TYPE_PERSON);
			map.put("name", person.getChineseName());
			commonMapper.updateObjectName(map);
		}else{
			Integer commonId=sequenceService.getNextId("pf_common");
			person.setId(commonId);
			personMapper.insertObject(person);
			CommonDomain common=new CommonDomain();
			common.setId(commonId);
			common.setSourceType(CommonDomain.SOURCE_TYPE_PERSON);
			common.setRecordId(person.getId());
			common.setParentId(new Integer(0));
			common.setName(person.getChineseName());
			common.setSysbol(common.getId());
			commonMapper.insertObject( common);
		}
		
		if(organizeId!=null && !organizeId.equals("")){
			OrganizeDomain organizeDomain=organizeMapper.getObjectVOById(new Integer(organizeId));
			CommonServiceImpl commonService=(CommonServiceImpl) SpringContextUtils.getBeanByClass(CommonServiceImpl.class);
			
			Map organizeRPersonMap=new HashMap();
				organizeRPersonMap.put("organizeId", organizeId);
				organizeRPersonMap.put("personId", person.getId());
			List organizeRPersonList=organizeRPersonMapper.getObjectVOList(organizeRPersonMap);
			if(organizeRPersonList==null || organizeRPersonList.size()==0){
				Integer commonId=sequenceService.getNextId("pf_common");
				OrganizeRPersonDomain organizeRPersonDomain=new OrganizeRPersonDomain();
				organizeRPersonDomain.setPersonId(person.getId());
				organizeRPersonDomain.setPersonName(person.getChineseName());
				organizeRPersonDomain.setOrganizeId(organizeDomain.getId());
				organizeRPersonDomain.setOrganizeName(organizeDomain.getName());
				organizeRPersonDomain.setId(commonId);
				organizeRPersonMapper.insertObject(organizeRPersonDomain);
				
				CommonDomain common=new CommonDomain();
				common.setId(commonId);
				common.setSourceType(new Integer(CommonDomain.SOURCE_TYPE_PERSON));
				common.setName(organizeRPersonDomain.getPersonName());
				common.setRecordId(organizeRPersonDomain.getPersonId());
				
				common.setParentId(organizeRPersonDomain.getOrganizeId());
				common.setSysbol(commonService.findCommonIdByPersonId(organizeRPersonDomain.getPersonId()));
				commonMapper.insertObject(common);
				
			}else{
				OrganizeRPersonDomain organizeRPersonDomain=(OrganizeRPersonDomain)organizeRPersonList.get(0);
				organizeRPersonDomain.setPersonName(person.getChineseName());
				organizeRPersonDomain.setOrganizeName(organizeDomain.getName());
				organizeRPersonMapper.updateObject(organizeRPersonDomain);
			}
		}
	}
	
	
	
}
