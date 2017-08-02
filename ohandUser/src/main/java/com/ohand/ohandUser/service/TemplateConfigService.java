package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.TemplateConfigDomain;

public interface TemplateConfigService {
	
	public TemplateConfigDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map);
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);
	
	public TemplateConfigDomain insertObject(TemplateConfigDomain vo);
	
	public Integer updateObject(TemplateConfigDomain vo);
	
	public void saveAndUpdateObject(TemplateConfigDomain vo,TemplateConfigDomain updateVo);
	
	public Integer deleteObjectById(Integer i);

}