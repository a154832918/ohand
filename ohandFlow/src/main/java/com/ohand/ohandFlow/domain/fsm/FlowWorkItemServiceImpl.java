package com.ohand.ohandFlow.domain.fsm;

import java.io.IOException;
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
import com.ohand.ohandFlow.domain.UserInfo;
import com.ohand.ohandFlow.mapper.FlowActivityMapper;
import com.ohand.ohandFlow.mapper.FlowInstMapper;
import com.ohand.ohandFlow.mapper.FlowWorkItemMapper;
import com.ohand.ohandFlow.script.ScriptFacade;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FlowWorkItemServiceImpl  implements  FlowWorkItemService {
	
	@Autowired
	private FlowWorkItemMapper flowWorkItemMapper;
	@Autowired
	private FlowActivityMapper flowActivityMapper;
	@Autowired
	private FlowInstMapper flowInstMapper;
	
	@Autowired
	private  SequenceService  sequenceService;

	public List getObjectMapList(Map map) {
		return flowWorkItemMapper.getObjectMapList( map);
	}

	public List getObjectVOList(Map map) {
		return flowWorkItemMapper.getObjectVOList( map);
	}

	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = flowWorkItemMapper.getObjectMapListPagination(
				 map);
		List count = (flowWorkItemMapper.getObjectMapListCount(
				 map));
		int numb = new Integer("" + ((Map) (count.get(0))).get("numb"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	public FlowWorkItemDomain insertObject(FlowWorkItemDomain vo) {
		vo.setId(sequenceService.getNextId("pf_flow_workitem"));
		flowWorkItemMapper.insertObject( vo);
		return vo;
	}

	/*
	 * 执行脚本   2014年6月29日
	 * parameter：
	 * 	 list:即将产生的工作项集合
	 * 	 workItemId:即将完成的工作项
	 */
	@Transactional
	public void saveInst(List list,String workItemId,UserInfo user,Integer flowInstId) throws BussinessException, IOException {
		
		FlowInstDomain flowInstDomain=flowInstMapper.getObjectVOById( flowInstId);
		
		FlowWorkItemDomain domain=null;
		if(workItemId!=null&&!workItemId.equals("")){
		    domain=flowWorkItemMapper.getObjectVOById( new Integer(workItemId));
			domain.setStatus(IWorkItemState.WORK_ITEM_STATUS_COMPLETED);
			flowWorkItemMapper.updateObject( domain);
			//TODO 执行脚本 完成后脚本
			Integer activityId=domain.getActivityId();
			FlowActivityDomain flowActivityDomain=flowActivityMapper.getObjectVOById(activityId);
			String scriptPath=flowActivityDomain.getScriptPath();
			Map retMap=ScriptFacade.executeWithScriptPath(scriptPath,FlowActivityDomain.SCRIPT_TYPE_flowExecAfter,user,domain,flowInstDomain);
		}
		
		
		if(list!=null){
			for(int i=0;i<list.size();i++){
				FlowWorkItemDomain vo=(FlowWorkItemDomain) list.get(i);
				if(vo.getActivityType().intValue()==FlowActivityDomain.FLOW_ITEM_TYPE_EndPointActivity.intValue()){
						flowInstDomain.setStatus(IFlowInstState.FLOW_INST_STATUS_COMPLETED);
					flowInstMapper.updateObject( flowInstDomain);
				}else{
					vo.setId(sequenceService.getNextId("pf_flow_workitem"));
					//当前登陆者
					vo.setSenderId(user.getId());
					vo.setSender(user.getUserName());
					flowWorkItemMapper.insertObject( vo);
					//TODO 执行  流程启动脚本
					FlowActivityDomain flowActivityDomain=flowActivityMapper.getObjectVOById(vo.getActivityId());
					String scriptPath=flowActivityDomain.getScriptPath();
					Map retMap=ScriptFacade.executeWithScriptPath(scriptPath,FlowActivityDomain.SCRIPT_TYPE_flowExecBefore,user,vo,flowInstDomain);
					
				}
			}
		}
	}
	
	public Integer updateObject(FlowWorkItemDomain vo) {
		return flowWorkItemMapper.updateObject( vo);
	}

	public Integer deleteObjectById(Integer i) {
		return flowWorkItemMapper.deleteObjectById( i);
	}
	
	// TODO 拼凑SQL
	public String packWorkItemSQL(String flowCode){
		StringBuffer sb=new StringBuffer();
		return "";
	}

	@Override
	public FlowWorkItemDomain getObjectVOById(Integer id) {
		return flowWorkItemMapper.getObjectVOById(id);
	}
	
}