package com.ohand.ohandFlow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormElementDomain;
import com.ohand.ohandFlow.mapper.FormElementMapper;
import com.ohand.ohandFlow.service.FormElementService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FormElementServiceImpl implements  FormElementService  {

	@Autowired
	private FormElementMapper formElementMapper;
	@Autowired
	private SequenceService  sequenceService;
	
	public FormElementDomain getObjectVOById(Integer id) {
		return formElementMapper.getObjectVOById( id);
	}
	public List getObjectMapList(Map map) {
		return formElementMapper.getObjectMapList(map);
	}
	public List getObjectVOList(Map map) {
		return formElementMapper.getObjectVOList(map);
	}
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=formElementMapper.getObjectMapListPagination(map);		 
		 List count=(formElementMapper.getObjectMapListCount(map));
		 int numb = ((Integer) count.get(0)).intValue();		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	public FormElementDomain insertObject(FormElementDomain vo) {
		vo.setId(sequenceService.getNextId("pf_form_element"));
		formElementMapper.insertObject(vo);
		return vo;
	}
	
	public Integer updateObject(FormElementDomain vo) {
		return formElementMapper.updateObject(vo);
	}
	
	public Integer deleteObjectById(Integer i) {
		return formElementMapper.deleteObjectById(i);
	}
	
}