package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormTemplateDomain;

public interface FormTemplateService {
	
	public FormTemplateDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);
	
	public FormTemplateDomain insertObject(FormTemplateDomain vo);
	
	public Integer updateObject(FormTemplateDomain vo);
	
	public Integer deleteObjectById(Integer i);
	
}