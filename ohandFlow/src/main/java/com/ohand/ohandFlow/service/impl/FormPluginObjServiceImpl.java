package com.ohand.ohandFlow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormPluginObjDomain;
import com.ohand.ohandFlow.domain.TableFieldIncrease;
import com.ohand.ohandFlow.mapper.FormPluginObjMapper;
import com.ohand.ohandFlow.mapper.TableFieldIncreaseMapper;
import com.ohand.ohandFlow.service.FormPluginObjService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FormPluginObjServiceImpl implements  FormPluginObjService  {
	
	@Autowired
	private FormPluginObjMapper formPluginObjMapper;
	@Autowired
	private TableFieldIncreaseMapper tableFieldIncreaseMapper;
	@Autowired
	private SequenceService  sequenceService;
	
	
	public FormPluginObjDomain getObjectVOById(Integer id) {
		return formPluginObjMapper.getObjectVOById( id);
	}
	
	public List getObjectMapList(Map map) {
		return formPluginObjMapper.getObjectMapList(map);
	}
	
	public List getObjectVOList(Map map) {
		return formPluginObjMapper.getObjectVOList(map);
	}
	
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=formPluginObjMapper.getObjectMapListPagination(map);		 
		 List count = (formPluginObjMapper.getObjectMapListCount(
					map));
		 int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}
	
	public FormPluginObjDomain insertObject(FormPluginObjDomain vo) {
		vo.setId(new Integer(sequenceService.getNextId("pf_form_plugin_obj")));
		formPluginObjMapper.insertObject(vo);
		return vo;
	}
	
	public Integer updateObject(FormPluginObjDomain vo) {
		return formPluginObjMapper.updateObject(vo);
	}
	
	public Integer deleteObjectById(Integer i) {
		return formPluginObjMapper.deleteObjectById(i);
	}
	
	public int getNextFlowCode() {
		TableFieldIncrease tableFieldIncrease = new TableFieldIncrease("pf_form_plugin_obj","plugin_code",-1);
		synchronized (this) {
			tableFieldIncrease = (TableFieldIncrease) tableFieldIncreaseMapper.getTableFieldIncrease(tableFieldIncrease);
			if (tableFieldIncrease == null) {
				throw new RuntimeException(
						"Ohand_Error: A null sequence was returned from the database (could not get next "
								+ " pf_form_plugin_obj , plugin_code  get error).");
			}
			TableFieldIncrease parameterObject = new TableFieldIncrease("pf_form_plugin_obj","plugin_code",tableFieldIncrease.getNextVal()+1);
			tableFieldIncreaseMapper.updateTableFieldIncrease(parameterObject);
		}
		return tableFieldIncrease.getNextVal();
	}
	
}