package com.ohand.ohandUser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.TemplateConfigDomain;
import com.ohand.ohandUser.mapper.TemplateConfigMapper;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.TemplateConfigService;
import com.ohand.ohandUser.service.UserService;

@Service
public class TemplateConfigServiceImpl implements TemplateConfigService {

	@Autowired
	private TemplateConfigMapper templateConfigMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public TemplateConfigDomain getObjectVOById(Integer id) {
		return templateConfigMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return templateConfigMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return templateConfigMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=templateConfigMapper.getObjectMapListPagination(map);		 
		 List count=(templateConfigMapper.getObjectMapListCount(map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		 return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public TemplateConfigDomain insertObject(TemplateConfigDomain vo) {
		vo.setId(sequenceService.getNextId("pf_template_config"));
		templateConfigMapper.insertObject(vo);
		return vo;
	}

	@Override
	public Integer updateObject(TemplateConfigDomain vo) {
		return templateConfigMapper.updateObject(vo);
	}

	@Override
	public void saveAndUpdateObject(TemplateConfigDomain vo, TemplateConfigDomain updateVo) {
		updateObject(updateVo);
		insertObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return templateConfigMapper.deleteObjectById(i);
	}

}
