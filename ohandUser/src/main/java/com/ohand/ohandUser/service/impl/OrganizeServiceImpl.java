package com.ohand.ohandUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.domain.OrganizeDomain;
import com.ohand.ohandUser.domain.OrganizeRPersonDomain;
import com.ohand.ohandUser.framework.SpringContextUtils;
import com.ohand.ohandUser.mapper.CommonMapper;
import com.ohand.ohandUser.mapper.OrganizeMapper;
import com.ohand.ohandUser.service.OrganizeService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;

@Service
public class OrganizeServiceImpl implements OrganizeService {

	@Autowired
	private OrganizeMapper organizeMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public OrganizeDomain getObjectVOById(Integer id) {
		return organizeMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return organizeMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return organizeMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=organizeMapper.getObjectMapListPagination(map);		 
		 List count = (organizeMapper.getObjectMapListCount(map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	@Transactional
	public void saveObjectCommonDomain(OrganizeDomain vo) {
		if(vo.getId()!=null&&vo.getId().intValue()>0){
			
			organizeMapper.updateObject(vo);
			
			CommonDomain common =commonMapper.getObjectVOById(vo.getId());
			common.setName(vo.getName());
			commonMapper.updateObject(common);
			
			OrganizeRPersonServiceImpl organizeRPersonService = (OrganizeRPersonServiceImpl) SpringContextUtils.getBeanByClass(OrganizeRPersonServiceImpl.class);
			Map organizeRPersonMap=new HashMap();
			organizeRPersonMap.put("organizeId", vo.getId());
			List organizeRPersonList=organizeRPersonService.getObjectVOList(organizeRPersonMap);
			if(organizeRPersonList!=null && organizeRPersonList.size()>0){
				for(int i=0;i<organizeRPersonList.size();i++){
					OrganizeRPersonDomain organizeRPersonDomain=(OrganizeRPersonDomain)organizeRPersonList.get(i);
					organizeRPersonDomain.setOrganizeName(vo.getName());
					organizeRPersonService.updateObject(organizeRPersonDomain);
				}
			}
			
		}else{
			Integer thisCommonId=sequenceService.getNextId("pf_common");
			CommonServiceImpl commonService=(CommonServiceImpl)SpringContextUtils.getBeanByClass(CommonServiceImpl.class);
			vo.setId(thisCommonId);
			organizeMapper.insertObject(vo);
			CommonDomain common=new CommonDomain();
			common.setId(thisCommonId);
			common.setSourceType(CommonDomain.SOURCE_TYPE_ORGANIZE);
			common.setRecordId(vo.getId());
			Integer parentCommonId=commonService.findCommonIdByOrgId(vo.getParentId());
			if(parentCommonId==null){
				parentCommonId=0;
			}
			common.setParentId(parentCommonId);
			common.setName(vo.getName());
			common.setSysbol(common.getId());
			commonMapper.insertObject( common);
		}
	}

	@Transactional
	public Integer deleteObjectById(Integer i) {
		OrganizeRPersonServiceImpl organizeRPersonService =(OrganizeRPersonServiceImpl) SpringContextUtils.getBeanByClass(OrganizeRPersonServiceImpl.class);
		organizeRPersonService.deleteObjectByOrganizeId(i);
		
		Map cMap=new HashMap();
		cMap.put("sourceType", CommonDomain.SOURCE_TYPE_ORGANIZE);
		cMap.put("recordId", i);
		commonMapper.deleteObjectByRecordId(cMap);
		
		Map rMap=new HashMap();
		rMap.put("sourceType", CommonDomain.SOURCE_TYPE_PERSON);
		rMap.put("parentId", i);
		commonMapper.deleteObjectByParentIdAndSourceType( rMap);			
		
		return organizeMapper.deleteObjectById(i);
		
	}

}
