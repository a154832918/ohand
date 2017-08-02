package com.ohand.ohandFlow.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.JsonUtil;
import com.ohand.ohandFlow.common.OhandFileUtil;
import com.ohand.ohandFlow.common.PFConstant;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.common.PropertyUtils;
import com.ohand.ohandFlow.domain.FlowActivityDomain;
import com.ohand.ohandFlow.domain.FlowConnectorDomain;
import com.ohand.ohandFlow.domain.FlowObjDomain;
import com.ohand.ohandFlow.domain.FlowWorkItemDomain;
import com.ohand.ohandFlow.domain.TableFieldIncrease;
import com.ohand.ohandFlow.domain.fsm.IWorkItemState;
import com.ohand.ohandFlow.mapper.FlowActivityMapper;
import com.ohand.ohandFlow.mapper.FlowConnectorMapper;
import com.ohand.ohandFlow.mapper.FlowObjMapper;
import com.ohand.ohandFlow.mapper.TableFieldIncreaseMapper;
import com.ohand.ohandFlow.service.FlowObjService;
import com.ohand.ohandFlow.service.SequenceService;
@Service
public class FlowObjServiceImpl implements FlowObjService {

	@Autowired
	private TableFieldIncreaseMapper tableFieldIncreaseMapper;
	@Autowired
	private FlowObjMapper flowObjMapper;
	@Autowired
	private FlowActivityMapper flowActivityMapper;
	@Autowired
	private FlowConnectorMapper flowConnectorMapper;
	@Autowired
	private SequenceService  sequenceService;

	
	public FlowObjDomain insertFlowObjDomain(FlowObjDomain flowObjDomain) {
		flowObjDomain.setId(new Integer(sequenceService.getNextId("pf_flow_obj")));
		flowObjMapper.insertObject(flowObjDomain);
		return flowObjDomain;
	}

	@Transactional
	public void insertFlowData(String flowdata, String flowObjId) {
		FlowObjDomain flowObj = getObjectVOById(new Integer(flowObjId));
		Map mapFlowObjId = new HashMap();
		mapFlowObjId.put("relatedFlowId", flowObj.getId());

		JSONObject jsonObj = JsonUtil.parseStr2JsonObjUnQuoted(flowdata);
		Map mapRect = (Map) jsonObj.get("states");
		Set<Map.Entry<String, Object>> setTask = mapRect.entrySet();
		Map<String, Integer> mapRectRelatedId = new HashMap<String, Integer>();

		List relatedItemList = flowActivityMapper.getObjectVOList( mapFlowObjId);
		if (relatedItemList != null && relatedItemList.size() > 0) {
			flowActivityMapper.deleteObjectByRelatedFlowId(flowObj.getId());
		}

		for (Iterator<Map.Entry<String, Object>> it = setTask.iterator(); it
				.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it
					.next();
			Integer itemId = new Integer(sequenceService.getNextId("pf_flow_activity"));
			mapRectRelatedId.put(entry.getKey(), itemId);
			Map itemMap = (Map) entry.getValue();
			FlowActivityDomain fItem = new FlowActivityDomain();
			fItem.setId(itemId);
			fItem.setActivityName("" + ((Map) itemMap.get("text")).get("text"));
			fItem.setActivityType(FlowActivityDomain.name2Id(""
					+ itemMap.get("type")));
			fItem.setActivityCode(entry.getKey()+"");
			fItem.setShowAttr((itemMap.get("attr") + "").replaceAll("\"", "\'"));
			fItem.setActivityDes((itemMap.get("props") + "").replaceAll("\"",
					"\'"));
			fItem.setRelatedFlowId(flowObj.getId());
			fItem.setRelatedFlowName(flowObj.getFlowName());
			fItem.setScriptPath(OhandFileUtil.OHAND_FILE_PATH + File.separator+ "flowfile"+File.separator+flowObj.getFlowCode()+File.separator+(flowObj.getUuid()+"").trim()+File.separator+fItem.getActivityCode());
			
			flowActivityMapper.insertFlowActivityDomain(fItem);
			
		}

		Map mapConnect = (Map) jsonObj.get("paths");
		Set<Map.Entry<String, Object>> setConnect = mapConnect.entrySet();

		List relatedConnectorList = flowConnectorMapper.getObjectVOList(mapFlowObjId);
		if (relatedConnectorList != null && relatedConnectorList.size() > 0) {
			flowConnectorMapper.deleteObjectByRelatedFlowId("pf_flow_connector",flowObj.getId());
		}

		for (Iterator<Map.Entry<String, Object>> it = setConnect.iterator(); it
				.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it
					.next();

			Map itemMap = (Map) entry.getValue();
			FlowConnectorDomain fConnector = new FlowConnectorDomain();
			fConnector.setId(new Integer(sequenceService.getNextId("pf_flow_connector")));
			fConnector.setPreId(mapRectRelatedId.get(itemMap.get("from") + ""));
			fConnector.setNextId(mapRectRelatedId.get(itemMap.get("to") + ""));
			fConnector
					.setConnName("" + ((Map) itemMap.get("text")).get("text"));
			JSONArray objJSONArray=(JSONArray)itemMap.get("dots");
			if(objJSONArray!=null&&objJSONArray.size()>1){
				String jsonTempStr="";
				for(int i=1;i<(objJSONArray.size()-1);i++){
					JSONObject jobjTemp=(JSONObject) objJSONArray.get(i);
					if(jsonTempStr.equals("")){
						jsonTempStr=jobjTemp+"";
					}else{
						jsonTempStr=jsonTempStr+","+jobjTemp;
					}
				}
				fConnector.setDots("["+jsonTempStr.replaceAll("\"","\'")+"]");
			}else{
				fConnector.setDots("[]");
			}
			fConnector.setTextPos(("" + itemMap.get("textPos")).replaceAll(
					"\"", "\'"));
			fConnector.setProps(("" + itemMap.get("props")).replaceAll("\"",
					"\'"));
			fConnector.setRelatedFlowId(flowObj.getId());
			fConnector.setRelatedFlowName(flowObj.getFlowName());

			flowConnectorMapper.insertFlowConnectorDomain(fConnector);

		}
		flowObj.setFlowStatus(FlowObjDomain.flowStatus_Edit);
		flowObjMapper.updateObject(flowObj);
	}

	public FlowObjDomain getObjectVOById(Integer id) {
		return flowObjMapper.getObjectVOById(id);
	}

	public List getObjectMapList(Map map) {
		return flowObjMapper.getObjectMapList(map);
	}

	public List getObjectVOList(Map map) {
		return flowObjMapper.getObjectVOList(map);
	}

	public Integer getMaxNextFlowCode() {
		List count = flowObjMapper.getMaxNextFlowCode();
		return ((Integer) (count.get(0))).intValue();
	}

	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = flowObjMapper.getObjectMapListPagination(
				map);
		List count = (flowObjMapper.getObjectMapListCount(
				map));
		int numb=((Integer)count.get(0)).intValue();
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	public FlowObjDomain insertObject(FlowObjDomain vo) {
		vo.setId(sequenceService.getNextId("pf_flow_obj"));
		flowObjMapper.insertObject(vo);
		return vo;
	}

	@Transactional
	public void insertObjectWithStatus(FlowObjDomain vo) throws IOException {
		//一、-------保存本记录操作---------------------------------------------------------------------
		Integer preLastVersion=null;
		Integer flowCode=vo.getFlowCode();
		if(flowCode==null ||flowCode.equals("")){
			throw new  BussinessException("传入的流程编码：唯一资源，出现差错...");
		}
		vo.setId(sequenceService.getNextId("pf_flow_obj"));
		vo.setFlowVersion(vo.getId());//设置当前版本
		vo.setCreateDate(new Date());
		vo.setUuid(UUID.randomUUID()+"");
		Map map=new HashMap();
		map.put("flowCode", flowCode);
		List flowCodeList=flowObjMapper.getObjectVOList(map);
		String oldUuid="";
		System.out.println("flowCode--------------"+flowCodeList.size());
		if(flowCodeList!=null && flowCodeList.size()>0){//修改操作
			// 获取最新记录
			int idMax=0;
			for(int i=0;i<flowCodeList.size();i++){
				FlowObjDomain biddingVOOutter=(FlowObjDomain) flowCodeList.get(i);
				Integer tempId=biddingVOOutter.getId();
				if(idMax<tempId.intValue()){
					idMax=tempId.intValue();
				}
			}
			int idIndex=0;
			for(int i=0;i<flowCodeList.size();i++){
				FlowObjDomain biddingVOOutter=(FlowObjDomain) flowCodeList.get(i);
				Integer tempId=biddingVOOutter.getId();
				if(idMax==tempId.intValue()){
					idIndex=i;
					break;
				}
			}
			FlowObjDomain preBiddingVO=(FlowObjDomain) flowCodeList.get(idIndex);
			oldUuid=preBiddingVO.getUuid();
			// 修改前的，最新版本
			preLastVersion=preBiddingVO.getLastVersion();
			for(int i=0;i<flowCodeList.size();i++){//1、更新pre 的最新记录的状态为 2  2、并设置最新版本
				FlowObjDomain preVO=(FlowObjDomain) flowCodeList.get(i);
				preVO.setIsLastVersion(new Integer(PFConstant.DIRT_IS_NO));
				preVO.setLastVersion(vo.getId());
				flowObjMapper.updateObject(preVO);
			}
			//设置vo的相关值
			vo.setIsLastVersion(new Integer(PFConstant.DIRT_IS_YES));
			vo.setLastVersion(vo.getId());
			vo.setBindingVersion(preBiddingVO.getBindingVersion());
		}else{// 第一次新增操作
			vo.setIsLastVersion(new Integer(PFConstant.DIRT_IS_YES));
			vo.setLastVersion(vo.getId());
		}
		flowObjMapper.insertObject(vo);
		//二、-------复制最新流程的关联信息(Activity、Connector表等)---------------------------------------
		if(preLastVersion==null){//保存操作
		}else{//修改操作
		
			Map mapItem = new HashMap();
			List itemList = null;
			List connectorList = null;
			if (preLastVersion != null && !preLastVersion.equals("")) {
				mapItem.put("relatedFlowId", preLastVersion);
				itemList = flowActivityMapper.getObjectVOList(mapItem);
				connectorList = flowConnectorMapper.getObjectVOList( mapItem);
			}
			Map oldMappingNew = new HashMap();
			if (itemList != null) {
				for (int i = 0; i < itemList.size(); i++) {
					FlowActivityDomain fItem = (FlowActivityDomain) itemList.get(i);
					Integer id = sequenceService.getNextId("pf_flow_activity");
					oldMappingNew.put(fItem.getId(), id + "");
					fItem.setId(id);
					fItem.setRelatedFlowId(vo.getId());
					fItem.setRelatedFlowName(vo.getFlowName());
					
					String activityDes=fItem.getActivityDes();
					JSONObject jObj=JsonUtil.parseStr2JsonObj(activityDes);
					Set retResult=jObj.entrySet();
					Iterator iterator=retResult.iterator();
					while(iterator.hasNext()){
						Map.Entry obj=(Map.Entry) iterator.next();
						
						String key=obj.getKey()+"";
						Map oo=(Map) obj.getValue();
						if(key.equals("dbId")){
							iterator.remove();
						}
						if(key.equals("activityCode")){
							iterator.remove();
						}
					}
					fItem.setActivityDes(JsonUtil.obj2JsonUseSingleQuotes(jObj));
					String scriptPath=fItem.getScriptPath();
					int splitLen=72+(File.separator+"").length();
					if(scriptPath.length()>splitLen){
						scriptPath=scriptPath.substring(0,(scriptPath.length()-splitLen))+vo.getUuid()+File.separator+fItem.getActivityCode();
					}
					fItem.setScriptPath(scriptPath);
					flowActivityMapper.insertObject(fItem);
				}
			}
			if (connectorList != null) {
				for (int j = 0; j < connectorList.size(); j++) {
					FlowConnectorDomain fConnector = (FlowConnectorDomain) connectorList
							.get(j);
					fConnector.setId(sequenceService.getNextId("pf_flow_connector"));
					fConnector.setRelatedFlowId(vo.getId());
					fConnector.setRelatedFlowName(vo.getFlowName());
	
					fConnector.setPreId(new Integer(""
							+ oldMappingNew.get(fConnector.getPreId())));
					fConnector.setNextId(new Integer(""
							+ oldMappingNew.get(fConnector.getNextId())));
	
					flowConnectorMapper.insertObject(fConnector);
				}
			}
		}
		//三、-------创建对应的分表---------------------------------------
		String tableName="pf_workitem"+flowCode;
		createTable(tableName);
		
		//四、-------流程脚本文件复制---------------------------------------	
		// TODO   传入旧的ActivityId集合与新ActivityId集合关系Map
		OhandFileUtil.copyFlowDesignFile(flowCode+"",oldUuid,vo.getUuid());
		
	}
	
	public Integer updateObject(FlowObjDomain vo) {
		return flowObjMapper.updateObject(vo);
	}

	public Integer deleteObjectById(Integer i) {
		return flowObjMapper.deleteObjectById(i);
	}
	
	public Integer deleteObjectByFlowCode(Integer i) {
		return flowObjMapper.deleteObjectByFlowCode(i);
	}
	
	public String wrapperItem2Json(List itemList, List connectorList,FlowObjDomain obj) {
		int len = 0;
		if ((itemList != null && itemList.size() >= 0)
				&& (connectorList != null && connectorList.size() >= 0)) {
			len = itemList.size() + connectorList.size();
		} else {
			throw new BussinessException("不存在活动环节！");
		}
		Map idNameMap = new HashMap();
		StringBuffer retJson = new StringBuffer();
		retJson.append("{states:{");
		for (int i = 0; i < itemList.size(); i++) {
			FlowActivityDomain fItem = (FlowActivityDomain) itemList.get(i);
			idNameMap.put(fItem.getId(), fItem.getActivityCode());
			retJson.append(fItem.getActivityCode() + ":{"); // TODO 设置成props 的id属性值啊！
			retJson.append("type:'"
					+ FlowActivityDomain.id2Name(fItem.getActivityType())
					+ "',");
			retJson.append("text:{text:'" + fItem.getActivityName() + "'},");
			retJson.append("attr:" + fItem.getShowAttr() + ",");
			String dbId=",'dbId':{'value':'"+fItem.getId()+"'}";
			String activityCode=",'activityCode':{'value':'"+fItem.getActivityCode()+"'}";
			if ((i + 1) == itemList.size()) {
				retJson.append("props:" + fItem.getActivityDes()+dbId+activityCode + "}");
			} else {
				retJson.append("props:" + fItem.getActivityDes()+dbId+activityCode+"},");
			}
		}
		retJson.append("},paths:{ ");
		for (int j = 0; j < connectorList.size(); j++) {
			FlowConnectorDomain fConnector = (FlowConnectorDomain) connectorList
					.get(j);
			retJson.append("path_" + (itemList.size() + j + 1) + ":{");
			retJson.append("from:'" + idNameMap.get(fConnector.getPreId())
					+ "',");
			retJson.append("to:'" + idNameMap.get(fConnector.getNextId())
					+ "',");
			retJson.append("dots:" + fConnector.getDots() + ",");
			retJson.append("text:{text:'" + fConnector.getConnName() + "'},");
			retJson.append("textPos:" + fConnector.getTextPos() + ",");
			if ((j + 1) == connectorList.size()) {
				retJson.append("props:" + fConnector.getProps() + "}");
			} else {
				retJson.append("props:" + fConnector.getProps() + "},");
			}
		}
		retJson.append("},props:{props:{name:{value:'"+obj.getFlowName()+"'},key:{value:'"+obj.getId()+"'},desc:{value:''}}}}");
		return retJson + "";
	}
	
	public String wrapperActiveRects(List workItemList) {
		List currentWorkItem=new ArrayList();
		if(workItemList!=null&&workItemList.size()>0){
			for(int i=0;i<workItemList.size();i++){
				FlowWorkItemDomain workItem=(FlowWorkItemDomain) workItemList.get(i);
				if(workItem.getStatus().intValue()==IWorkItemState.WORK_ITEM_STATUS_NEW.intValue()){
					currentWorkItem.add(workItem);
				}
				if(workItem.getStatus().intValue()==IWorkItemState.WORK_ITEM_STATUS_PENDING.intValue()){
					currentWorkItem.add(workItem);
				}
				if(workItem.getStatus().intValue()==IWorkItemState.WORK_ITEM_STATUS_SAVED.intValue()){
					currentWorkItem.add(workItem);
				}
				if(workItem.getStatus().intValue()==IWorkItemState.WORK_ITEM_STATUS_URGED.intValue()){
					currentWorkItem.add(workItem);
				}
				if(workItem.getStatus().intValue()==IWorkItemState.WORK_ITEM_STATUS_RETURNED.intValue()){
					currentWorkItem.add(workItem);
				}	
				if(workItem.getStatus().intValue()==IWorkItemState.WORK_ITEM_STATUS_READ.intValue()){
					currentWorkItem.add(workItem);
				}					
			}
		}
		if(currentWorkItem!=null&&currentWorkItem.size()>0){
			StringBuffer retJson = new StringBuffer();
			retJson.append("\"activeRects\":{\"rects\":[");
			for(int i=0;i<currentWorkItem.size();i++){
				FlowWorkItemDomain workItem=(FlowWorkItemDomain) currentWorkItem.get(i);
				if(i==0){
					retJson.append("{\"paths\":[],\"name\":\""+ workItem.getActivityName()+"\"}");
				}else{
					retJson.append(",{\"paths\":[],\"name\":\""+ workItem.getActivityName()+"\"}");
				}
			}
			retJson.append("]}");
			return retJson+"";
		}else{
			StringBuffer retJson = new StringBuffer();
			retJson.append("\"activeRects\":{\"rects\":[");
			retJson.append("]}");
			return retJson+"";
		}
	}
	
	public String wrapperHistoryRects(List workItemList) {
		List historyWorkItem=new ArrayList();
		if(workItemList!=null&&workItemList.size()>0){
			for(int i=0;i<workItemList.size();i++){
				FlowWorkItemDomain workItem=(FlowWorkItemDomain) workItemList.get(i);
				if(workItem.getStatus().intValue()==IWorkItemState.WORK_ITEM_STATUS_RECLAIMED.intValue()){
					historyWorkItem.add(workItem);
				}
				if(workItem.getStatus().intValue()==IWorkItemState.WORK_ITEM_STATUS_OVER_DEADLINE.intValue()){
					historyWorkItem.add(workItem);
				}
				if(workItem.getStatus().intValue()==IWorkItemState.WORK_ITEM_STATUS_COMPLETED.intValue()){
					historyWorkItem.add(workItem);
				}				
			}
		}
		if(historyWorkItem!=null&&historyWorkItem.size()>0){
			StringBuffer retJson = new StringBuffer();
			retJson.append("\"historyRects\":{\"rects\":[");
			for(int i=0;i<historyWorkItem.size();i++){
				FlowWorkItemDomain workItem=(FlowWorkItemDomain) historyWorkItem.get(i);
				if(i==0){
					retJson.append("{\"paths\":[],\"name\":\""+ workItem.getActivityName()+"\"}");
				}else{
					retJson.append(",{\"paths\":[],\"name\":\""+ workItem.getActivityName()+"\"}");
				}
			}
			retJson.append("]}");
			return retJson+"";
		}else{
			StringBuffer retJson = new StringBuffer();
			retJson.append("\"historyRects\":{\"rects\":[");
			retJson.append("]}");
			return retJson+"";
		}
	}
	
	public int getNextFlowCode() {
		TableFieldIncrease tableFieldIncrease = new TableFieldIncrease("pf_flow_obj","flow_code",-1);
		synchronized (this) {
			tableFieldIncrease = (TableFieldIncrease) tableFieldIncreaseMapper.getTableFieldIncrease(tableFieldIncrease);
			if (tableFieldIncrease == null) {
				throw new RuntimeException(
						"Ohand_Error: A null sequence was returned from the database (could not get next "
								+ " pf_flow_obj , flow_code  get error).");
			}
			TableFieldIncrease parameterObject = new TableFieldIncrease("pf_flow_obj","flow_code",tableFieldIncrease.getNextVal()+1);
			tableFieldIncreaseMapper.updateTableFieldIncrease(parameterObject);
		}
		return tableFieldIncrease.getNextVal();
	}
	
	@Transactional
	public void flowVersionBinding(String id){
		
		FlowObjDomain obj=flowObjMapper.getObjectVOById(new Integer(id));
		obj.setBindingVersion(obj.getId());
		obj.setIsBindingVersion(new Integer(PFConstant.DIRT_IS_YES));
		flowObjMapper.updateObject(obj);
		
		Map map=new HashMap();
		map.put("flowCode", obj.getFlowCode());
		List retList=flowObjMapper.getObjectVOList(map);
		if(retList!=null){
			for(int i=0;i<retList.size();i++){
				FlowObjDomain innerVO=(FlowObjDomain) retList.get(i);
				if(!id.equals(innerVO.getId()+"")){//除了绑定的这条记录外，其余状态都得改变
					innerVO.setBindingVersion(obj.getId());
					innerVO.setIsBindingVersion(new Integer(PFConstant.DIRT_IS_NO));
					flowObjMapper.updateObject(innerVO);
				}
			}
		}
	}
	
	@Transactional
	public void createTable(String tableName){
		String dbType=PropertyUtils.getEntryValue("app.properties", "dbType");
		boolean isExists=false;
		Map isExistMap=new HashMap();
		isExistMap.put("dbType", dbType);
		isExistMap.put("tableName", tableName.toUpperCase());
		if(dbType!=null&&dbType.toLowerCase().equals("oracle")){
			Integer count=flowObjMapper.isExistsTableName(isExistMap);
			if(count>0){
				isExists=true;
			}else{
				isExists=false;
			}
		}else if((dbType!=null&&dbType.toLowerCase().equals("mysql"))){
			Integer count=flowObjMapper.isExistsTableName(isExistMap);
			if(count>0){
				isExists=true;
			}else{
				isExists=false;
			}
		}else{//TODO其他类型
			
		}
		if(!isExists){
			
			String sql=" create table "+tableName +" "
							+" ( "
							+" Id                 INTEGER                not null, "
							+" Flow_Id            INTEGER, "
							+" Flow_Name          VARCHAR(128), "
							+" Flow_Inst_Id       INTEGER, "
							+" Create_Date        TIMESTAMP, "
							+" Status             INTEGER, "
							+" Sender             VARCHAR(64), "
							+" Sender_Id          INTEGER, "
							+" Receiver           VARCHAR(64), "
							+" Receiver_Id        INTEGER, "
							+" Source_Type        INTEGER, "
							+" Read_Date          TIMESTAMP, "
							+" Deal_Date          TIMESTAMP, "
							+" Activity_Id        INTEGER, "
							+" Activity_Name      VARCHAR(128), "
							+" Flow_Code          INTEGER, "
							+" Form_Id            INTEGER, "
							+" Form_Code          INTEGER, "
							+" Form_Name          VARCHAR(128), "
							+" Rid                INTEGER, "
							+" Title              VARCHAR(128), "
							+" constraint temp_"+tableName+"  primary key (Id) "
							+" ) ";
			FlowObjDomain domain=new FlowObjDomain();
			domain.setCreateTableSql(sql);
			flowObjMapper.createTable(domain);
		}
		//往主键表插入一条记录
	}
	
	//更新pf_flow_activity表的script_path字段
	@Transactional
	public void saveActivityScriptFile(String type,String uuid, String itemId, String htmlData) {

		Map map=new HashMap();
		map.put("uuid", uuid);
		List list=flowObjMapper.getObjectVOList(map);
		if(list==null || list.size()==0){
			throw new BussinessException("未保存流程对象...");
		}
		FlowObjDomain obj=(FlowObjDomain) list.get(0);
		OhandFileUtil.saveActivityScriptFile(obj.getFlowCode()+"",uuid,itemId+"_"+type,htmlData);
	}
	
}
