package com.ohand.ohandFlow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormElementRightDomain;
import com.ohand.ohandFlow.mapper.FormElementRightMapper;
import com.ohand.ohandFlow.service.FormElementRightService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FormElementRightServiceImpl  implements  FormElementRightService  {
	
	@Autowired
	private FormElementRightMapper formElementRightMapper;
	@Autowired
	private SequenceService  sequenceService;
	
	
	public FormElementRightMapper getFormElementRightMapper() {
		return formElementRightMapper;
	}
	
	@Resource
	public void setFormElementRightMapper(
			FormElementRightMapper formElementRightMapper) {
		this.formElementRightMapper = formElementRightMapper;
	}
	
	public FormElementRightDomain getObjectVOById(Integer id) {
		return formElementRightMapper.getObjectVOById(id);
	}
	
	public List getObjectMapList(Map map) {
		return formElementRightMapper.getObjectMapList(map);
	}
	
	public List getObjectVOList(Map map) {
		return formElementRightMapper.getObjectVOList(map);
	}
	
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=formElementRightMapper.getObjectMapListPagination(map);		 
		 List count=(formElementRightMapper.getObjectMapListCount(map));
		 int numb = ((Integer) count.get(0)).intValue();		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	public FormElementRightDomain insertObject(FormElementRightDomain vo) {
		vo.setId(sequenceService.getNextId("pf_form_element_right"));
		formElementRightMapper.insertObject(vo);
		return vo;
	}
	
	public Integer updateObject(FormElementRightDomain vo) {
		return formElementRightMapper.updateObject(vo);
	}

	public Integer deleteObjectById(Integer i) {
		return formElementRightMapper.deleteObjectById(i);
	}

	@Transactional
	public void ohandPluginPageItemPermissionSave(List list) {
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				FormElementRightDomain obj=(FormElementRightDomain)list.get(i);
				Map tempMap=new HashMap();
					tempMap.put("formRFlowId", obj.getFormRFlowId());
					tempMap.put("activityId", obj.getActivityId());
					tempMap.put("elementCode", obj.getElementCode());
				List countList=formElementRightMapper.getObjectVOList(tempMap);
				if(countList!=null&&countList.size()>0){
					FormElementRightDomain dbVO=(FormElementRightDomain)countList.get(0);
					obj.setId(dbVO.getId());
					updateObject(obj);
				}else{
					insertObject(obj);
				}
			}
		}
	}
	
}