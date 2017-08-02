package com.ohand.ohandFlow.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowObjDomain;

public interface FlowObjService  {

	public FlowObjDomain insertFlowObjDomain(FlowObjDomain flowObjDomain);

	public void insertFlowData(String flowdata, String flowObjId);

	public FlowObjDomain getObjectVOById(Integer id);
	
	public List getObjectMapList(Map map) ;
	
	public List getObjectVOList(Map map);

	public Integer getMaxNextFlowCode() ;

	public Pagination getObjectVOListPage(Map map) ;

	public FlowObjDomain insertObject(FlowObjDomain vo) ;

	public void insertObjectWithStatus(FlowObjDomain vo) throws IOException ;
	
	public Integer updateObject(FlowObjDomain vo);
	
	public Integer deleteObjectById(Integer i) ;
	
	public Integer deleteObjectByFlowCode(Integer i);
	
	public String wrapperItem2Json(List itemList, List connectorList,FlowObjDomain obj) ;
	
	public String wrapperActiveRects(List workItemList);
	
	public String wrapperHistoryRects(List workItemList);
	
	public int getNextFlowCode();
	
	public void flowVersionBinding(String id);
	
	public void createTable(String tableName);
	
	public void saveActivityScriptFile(String type,String uuid, String itemId, String htmlData);
	
}
