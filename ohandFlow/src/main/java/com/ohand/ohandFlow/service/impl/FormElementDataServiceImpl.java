package com.ohand.ohandFlow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormElementDataDomain;
import com.ohand.ohandFlow.mapper.FormElementDataMapper;
import com.ohand.ohandFlow.service.FormElementDataService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FormElementDataServiceImpl implements FormElementDataService  {
	
	@Autowired
	private FormElementDataMapper formElementDataMapper;
	
	@Autowired
	private SequenceService  sequenceService;
	
	public FormElementDataDomain getObjectVOById(Integer id) {
		return formElementDataMapper.getObjectVOById(id);
	}
	
	public List getObjectMapList(Map map) {
		return formElementDataMapper.getObjectMapList(map);
	}
	
	public List getObjectVOList(Map map) {
		return formElementDataMapper.getObjectVOList(map);
	}
	
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=formElementDataMapper.getObjectMapListPagination(map);		 
		 List count = (formElementDataMapper.getObjectMapListCount(
					map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));			 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	public FormElementDataDomain insertObject(FormElementDataDomain vo) {
		vo.setId(sequenceService.getNextId("pf_form_element_data"));
		formElementDataMapper.insertObject(vo);
		return vo;
	}
	
	@Transactional
	public void insertObjectDomainList(List  list) {
		if(list!=null&&list.size()>0){
			Map map=new HashMap();
			FormElementDataDomain mapVO=(FormElementDataDomain) list.get(0);
			map.put("flowInstId", mapVO.getFlowInstId());
			List formElementDataList=formElementDataMapper.getObjectVOList(map);
			for(int i=0;i<list.size();i++){
				FormElementDataDomain vo=(FormElementDataDomain) list.get(i);
				if(formElementDataList!=null&&formElementDataList.size()>0){
					boolean isInFormElementDataList=false;//是否在formElementDataList列表里面
					for(int j=0;j<formElementDataList.size();j++){
						FormElementDataDomain voInner=(FormElementDataDomain) formElementDataList.get(j);
						if(voInner.getElementCode().equals(vo.getElementCode())){//寻找相同elementCode的记录
							isInFormElementDataList=true;
							if((voInner.getElementValue()+"").equals(vo.getElementValue())){//如果值相等，就不执行更新操作了...
								break;
							}else{
								voInner.setElementValue(vo.getElementValue());
								formElementDataMapper.updateObject(voInner);
								break;
							}
						}
					}
					//保存不在formElementDataList列表里面的对象
					if(isInFormElementDataList==false){
						vo.setId(sequenceService.getNextId("pf_form_element_data"));
						formElementDataMapper.insertObject(vo);
					}
					
				}else{
					vo.setId(sequenceService.getNextId("pf_form_element_data"));
					formElementDataMapper.insertObject(vo);
				}
			}
		}
	}
	
	public Integer updateObject(FormElementDataDomain vo) {
		return formElementDataMapper.updateObject(vo);
	}

	public Integer deleteObjectById(Integer i) {
		return formElementDataMapper.deleteObjectById(i);
	}
	
}