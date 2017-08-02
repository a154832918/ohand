package com.ohand.ohandFlow.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormTemplateDomain;
import com.ohand.ohandFlow.mapper.FormTemplateMapper;
import com.ohand.ohandFlow.service.FormTemplateService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FormTemplateServiceImpl implements  FormTemplateService  {
	
	@Autowired
	private FormTemplateMapper formTemplateMapper;
	
	@Autowired
	private SequenceService  sequenceService;
	
	public FormTemplateDomain getObjectVOById(Integer id) {
		return formTemplateMapper.getObjectVOById(id);
	}
	
	public List getObjectMapList(Map map) {
		return formTemplateMapper.getObjectMapList(map);
	}
	
	public List getObjectVOList(Map map) {
		return formTemplateMapper.getObjectVOList(map);
	}
	
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList = formTemplateMapper.getObjectMapListPagination(
					 map);
		 List count = (formTemplateMapper.getObjectMapListCount(
					map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}
	
	public FormTemplateDomain insertObject(FormTemplateDomain vo) {
		vo.setId(sequenceService.getNextId("pf_form_template"));
		formTemplateMapper.insertObject(vo);
		return vo;
	}
	
	public Integer updateObject(FormTemplateDomain vo) {
		return formTemplateMapper.updateObject(vo);
	}
	
	public Integer deleteObjectById(Integer i) {
		return formTemplateMapper.deleteObjectById(i);
	}
	
}