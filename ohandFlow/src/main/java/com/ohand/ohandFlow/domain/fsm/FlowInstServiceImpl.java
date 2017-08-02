
package com.ohand.ohandFlow.domain.fsm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowActivityDomain;
import com.ohand.ohandFlow.domain.FlowInstDomain;
import com.ohand.ohandFlow.domain.FlowWorkItemDomain;
import com.ohand.ohandFlow.domain.FormElementDataDomain;
import com.ohand.ohandFlow.domain.FormRFlowDomain;
import com.ohand.ohandFlow.domain.UserInfo;
import com.ohand.ohandFlow.mapper.FlowActivityMapper;
import com.ohand.ohandFlow.mapper.FlowInstMapper;
import com.ohand.ohandFlow.mapper.FlowWorkItemMapper;
import com.ohand.ohandFlow.mapper.FormElementDataMapper;
import com.ohand.ohandFlow.mapper.FormRFlowMapper;
import com.ohand.ohandFlow.service.SequenceService;


@Service
class FlowInstServiceImpl  implements FlowInstService  {

	@Autowired
	private FlowInstMapper flowInstMapper;
	@Autowired
	private FormElementDataMapper formElementDataMapper;
	@Autowired
	private FormRFlowMapper formRFlowMapper;
	@Autowired
	private FlowActivityMapper flowActivityMapper;
	@Autowired
	private FlowWorkItemMapper flowWorkItemMapper;
	@Autowired
	private  SequenceService  sequenceService;
	
	
	public FlowWorkItemMapper getFlowWorkItemMapper() {
		return flowWorkItemMapper;
	}
	@Resource
	public void setFlowWorkItemMapper(FlowWorkItemMapper flowWorkItemMapper) {
		this.flowWorkItemMapper = flowWorkItemMapper;
	}
	public FormRFlowMapper getFormRFlowMapper() {
		return formRFlowMapper;
	}
	@Resource
	public void setFormRFlowMapper(FormRFlowMapper formRFlowMapper) {
		this.formRFlowMapper = formRFlowMapper;
	}
	public FormElementDataMapper getFormElementDataMapper() {
		return formElementDataMapper;
	}
	@Resource
	public void setFormElementDataMapper(FormElementDataMapper formElementDataMapper) {
		this.formElementDataMapper = formElementDataMapper;
	}
	public FlowInstMapper getFlowInstMapper() {
		return flowInstMapper;
	}
	@Resource
	public void setFlowInstMapper(FlowInstMapper flowInstMapper) {
		this.flowInstMapper = flowInstMapper;
	}
	public FlowActivityMapper getFlowActivityMapper() {
		return flowActivityMapper;
	}
	@Resource
	public void setFlowActivityMapper(FlowActivityMapper flowActivityMapper) {
		this.flowActivityMapper = flowActivityMapper;
	}
	public FlowInstDomain getObjectVOById(Integer id) {
		return flowInstMapper.getObjectVOById(id);
	}
	public List getObjectMapList(Map map) {
		return flowInstMapper.getObjectMapList(map);
	}
	public List getObjectVOList(Map map) {
		return flowInstMapper.getObjectVOList(map);
	}
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=flowInstMapper.getObjectMapListPagination(map);		 
		 List count = (flowInstMapper.getObjectMapListCount(
					map));
			int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));			 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}
	
	public FlowInstDomain insertObject(FlowInstDomain vo) {
		vo.setId(sequenceService.getNextId("pf_flow_inst"));
		flowInstMapper.insertObject(vo);
		return vo;
	}

	/**
	 * 一、保存表单数据
	 * 二、保存用户创建工作项
	 */
	@Transactional
	public void saveInst(FlowInstDomain vo,List elementDataList,UserInfo userInfo) {
		vo.setId(sequenceService.getNextId("pf_flow_inst"));
		flowInstMapper.insertObject(vo);
		if(elementDataList.size()>0){
			// 查询出来FormElementData数据......
			Map elementDataMap=new HashMap();
			elementDataMap.put("flowInstId", vo.getId());
			List dbElementDataList=formElementDataMapper.getObjectVOList( elementDataMap);
			
			for(int i=0;i<elementDataList.size();i++){
				FormElementDataDomain domain=(FormElementDataDomain)elementDataList.get(i);
				if(isExistsInList(dbElementDataList,domain)){
					formElementDataMapper.updateObject( domain);
				}else{
					domain.setFlowInstId(vo.getId());
					domain.setId(sequenceService.getNextId("pf_form_element_data"));
					formElementDataMapper.insertObject(domain);
				}
			}
		}
		
		FormRFlowDomain	formRFlow=formRFlowMapper.getObjectVOById(vo.getrId());
		Map flowActivityMap=new HashMap();
			flowActivityMap.put("relatedFlowId", vo.getFlowId());
			flowActivityMap.put("activityType", FlowActivityDomain.FLOW_ITEM_TYPE_StartPointActivity);
		List flowActivityList=flowActivityMapper.getObjectVOList( flowActivityMap);
		FlowActivityDomain flowActivity=new FlowActivityDomain();
		if(flowActivityList!=null&&flowActivityList.size()>0){
			flowActivity=(FlowActivityDomain)flowActivityList.get(0);
		}else{
			throw new BussinessException("本流程没有开始节点！");
		}
		
		FlowWorkItemDomain workItem=new FlowWorkItemDomain();
			workItem.setId(sequenceService.getNextId("pf_flow_workitem"));
			workItem.setrId(vo.getrId());
			workItem.setTitle(vo.getTitle());
			workItem.setFlowId(formRFlow.getFlowId());
			workItem.setFlowCode(formRFlow.getFlowCode());
			workItem.setFlowName(formRFlow.getFlowName());
			workItem.setFormId(formRFlow.getFormId());
			workItem.setFormCode(formRFlow.getFormCode());
			workItem.setFormName(formRFlow.getFormName());
			workItem.setReceiver(userInfo.getUserName());
			workItem.setReceiverId(userInfo.getId());
			workItem.setFlowInstId(new Integer(vo.getId()));
			workItem.setStatus(IWorkItemState.WORK_ITEM_STATUS_SAVED);
			workItem.setActivityId(flowActivity.getId());
			workItem.setActivityName(flowActivity.getActivityName());
			workItem.setCreateDate(new Date());
			workItem.setActivityType(FlowActivityDomain.FLOW_ITEM_TYPE_StartPointActivity);
			flowWorkItemMapper.insertObject(workItem);
		
	}
	
	public boolean isExistsInList(List<FormElementDataDomain> list,FormElementDataDomain vo){
		if(list==null|| list.size()==0){
			return false;
		}else{
			for(int i=0;i<list.size();i++){
				FormElementDataDomain domain=(FormElementDataDomain) list.get(i);
				if(domain.getElementCode()!=null&&vo.getElementCode()!=null&&domain.getElementCode().equals(vo.getElementCode())){//elementCode,
					return true ;
				}
			}
			return false;
		}
	}
	
	public Integer updateObject(FlowInstDomain vo) {
		return flowInstMapper.updateObject(vo);
	}

	public Integer deleteObjectById(Integer i) {
		return flowInstMapper.deleteObjectById(i);
	}

}