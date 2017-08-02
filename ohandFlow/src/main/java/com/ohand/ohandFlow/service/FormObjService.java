package com.ohand.ohandFlow.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormObjDomain;

public interface FormObjService {
	
	public FormObjDomain getObjectVOById(Integer id) ;

	public List getObjectMapList(Map map);

	public List getObjectVOList(Map map);
	
	public Pagination getObjectVOListPage(Map map);
	
	public void insertObjectWithStatus(FormObjDomain vo,String preFormObjId) throws IOException;
	
	public FormObjDomain insertObject(FormObjDomain vo);

	public Integer updateObject(FormObjDomain vo) ;
	
	public Integer deleteObjectById(Integer i) ;

	public void saveFormFile(String formId,String htmlData,String elementObjSplits);
	
	public void savePortletFile(String formId,String portletUuid,String htmlData,String elementObjSplits);
	
	public void saveFormInitScriptFile(String formId,String htmlData);
	
	public int getNextFormCode();
	
	public void formVersionBinding(String id);
	
}