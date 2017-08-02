package com.ohand.ohandFlow.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormLayoutDomain;

public interface FormLayoutService {
	
	public FormLayoutDomain getObjectVOById(Integer id) ;
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map) ;
	
	public FormLayoutDomain insertObject(FormLayoutDomain vo);
	
	public Integer updateObject(FormLayoutDomain vo);
	
	public void layoutChange(String formObjId,JSONArray jsonArray);
	
	public Integer deleteObjectById(Integer i) ;
	
	public Integer deleteObjectByLayoutUuid(Map layoutUuid);

}