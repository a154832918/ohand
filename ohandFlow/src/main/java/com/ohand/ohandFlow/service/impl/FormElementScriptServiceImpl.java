package com.ohand.ohandFlow.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.common.UUIDUtil;
import com.ohand.ohandFlow.domain.FormElementScriptDomain;
import com.ohand.ohandFlow.mapper.FormElementScriptMapper;
import com.ohand.ohandFlow.service.FormElementScriptService;

@Service
public class FormElementScriptServiceImpl  implements FormElementScriptService  {
	
	@Autowired
	private FormElementScriptMapper formElementScriptMapper;
	
	public FormElementScriptDomain getObjectVOById(Integer id) {
		return formElementScriptMapper.getObjectVOById(id);
	}
	
	public List getObjectMapList(Map map) {
		return formElementScriptMapper.getObjectMapList(map);
	}
	
	public List getObjectVOList(Map map) {
		return formElementScriptMapper.getObjectVOList(map);
	}
	
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=formElementScriptMapper.getObjectMapListPagination(map);		 
		 List count=(formElementScriptMapper.getObjectMapListCount(map));
		 int numb = ((Integer) count.get(0)).intValue();		 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	public FormElementScriptDomain insertObject(FormElementScriptDomain vo) {
		String uuid=UUIDUtil.generateUUID();
		vo.setId(uuid);
		formElementScriptMapper.insertObject(vo);
		return vo;
	}
	
	@Transactional
	public FormElementScriptDomain insertObjectWithVersion(FormElementScriptDomain vo) {
		
		if(vo.getElementUuid()==null || vo.getElementUuid().equals("")){
			throw new BussinessException("传入系统参数uuid异常！");
		}
		
		Map preMap=new HashMap();
			preMap.put("elementUuid", vo.getElementUuid());
		List preList=getObjectVOList(preMap);
		for(int i=0;i<preList.size();i++){
			FormElementScriptDomain domain=(FormElementScriptDomain)preList.get(i);
			domain.setIsLast(FormElementScriptDomain.IS_LAST_NO);
			formElementScriptMapper.updateObject(domain);
		}
		
		String uuid=UUIDUtil.generateUUID();
		vo.setId(uuid);
		vo.setIsLast(FormElementScriptDomain.IS_LAST_YES);
		vo.setCreateTime(new Date());
		formElementScriptMapper.insertObject(vo);
		return vo;
	}
	
	
	public Integer updateObject(FormElementScriptDomain vo) {
		return formElementScriptMapper.updateObject(vo);
	}

	public Integer deleteObjectById(Integer i) {
		return formElementScriptMapper.deleteObjectById(i);
	}

}