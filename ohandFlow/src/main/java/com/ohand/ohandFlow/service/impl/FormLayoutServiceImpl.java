package com.ohand.ohandFlow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormLayoutDomain;
import com.ohand.ohandFlow.mapper.FormLayoutMapper;
import com.ohand.ohandFlow.service.FormLayoutService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FormLayoutServiceImpl  implements FormLayoutService  {
	
	@Autowired
	private FormLayoutMapper formLayoutMapper;
	@Autowired
	private SequenceService  sequenceService;
	
	public FormLayoutDomain getObjectVOById(Integer id) {
		return formLayoutMapper.getObjectVOById(id);
	}
	
	public List getObjectMapList(Map map) {
		return formLayoutMapper.getObjectMapList(map);
	}
	
	public List getObjectVOList(Map map) {
		return formLayoutMapper.getObjectVOList(map);
	}
	
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList = formLayoutMapper.getObjectMapListPagination(
					 map);
		 List count = (formLayoutMapper.getObjectMapListCount(
					map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}
	
	public FormLayoutDomain insertObject(FormLayoutDomain vo) {
		vo.setId(sequenceService.getNextId("pf_form_layout"));
		formLayoutMapper.insertObject(vo);
		return vo;
	}
	
	public Integer updateObject(FormLayoutDomain vo) {
		return formLayoutMapper.updateObject(vo);
	}
	
	public void layoutChange(String formObjId,JSONArray jsonArray){
		Map map=new HashMap();
			map.put("formId", formObjId);
		List objList=getObjectVOList(map);
		if(objList!=null&&objList.size()>0){
			for(int i=0;i<objList.size();i++){
				FormLayoutDomain formLayoutDomain=(FormLayoutDomain)objList.get(i);
				String outterLayoutUuid=formLayoutDomain.getLayoutUuid();
				boolean isChange=false;
				if(jsonArray!=null&&jsonArray.size()>0){
					for(int j=0;j<jsonArray.size();j++){
						JSONObject jsonObj=(JSONObject)jsonArray.get(j);
						String parentUuid=""+jsonObj.get("parentUuid");
						String id=""+jsonObj.get("id");
						if(outterLayoutUuid.equals(id)){
							if(parentUuid.equals(formLayoutDomain.getParentUuid())){
								isChange=false;
							}else{
								isChange=true;
								formLayoutDomain.setParentUuid(parentUuid);
							}
							break;
						}
					}
				}
				if(isChange){
					updateObject(formLayoutDomain);
				}
			}
		}
	}
	
	public Integer deleteObjectById(Integer i) {
		return formLayoutMapper.deleteObjectById(i);
	}
	
	public Integer deleteObjectByLayoutUuid(Map layoutUuid) {
		return formLayoutMapper.deleteObjectByLayoutUuid(layoutUuid);
	}

}