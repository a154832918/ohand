package com.ohand.ohandFlow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.common.exception.BussinessException;
import com.ohand.common.exception.BussinessExceptionCode;
import com.ohand.ohandFlow.common.PFConstant;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FormObjDomain;
import com.ohand.ohandFlow.domain.FormRFlowDomain;
import com.ohand.ohandFlow.mapper.FormObjMapper;
import com.ohand.ohandFlow.mapper.FormRFlowMapper;
import com.ohand.ohandFlow.service.FormRFlowService;
import com.ohand.ohandFlow.service.SequenceService;
@Service("formRFlowService")
public class FormRFlowServiceImpl implements FormRFlowService {

	@Autowired
	private FormRFlowMapper formRFlowMapper;
	@Autowired
	private FormObjMapper formObjMapper;
	@Autowired
	private SequenceService  sequenceService;
	
	public FormRFlowDomain getObjectVOById(Integer id) {
		return formRFlowMapper.getObjectVOById(id);
	}
	
	public List getObjectMapList(Map map) {
		return formRFlowMapper.getObjectMapList(map);
	}
	
	public List getObjectVOList(Map map) {
		return formRFlowMapper.getObjectVOList(map);
	}
	
	public List missiveTemplate(Map map) {
		return formRFlowMapper.missiveTemplate(map);
	}
	
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=formRFlowMapper.getObjectMapListPagination(map);		 
		 List count=(formRFlowMapper.getObjectMapListCount(map));
		 int numb = ((Integer) count.get(0)).intValue();	 
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}

	public FormRFlowDomain insertObject(FormRFlowDomain vo) {
		vo.setId(sequenceService.getNextId("pf_form_r_flow"));
		formRFlowMapper.insertObject(vo);
		return vo;
	}
	
	public Integer updateObject(FormRFlowDomain vo) {
		return formRFlowMapper.updateObject(vo);
	}

	public Integer deleteObjectById(Integer i) {
		return formRFlowMapper.deleteObjectById(i);
	}
	
	@Transactional
	public Integer deleteObjectByFormCode(Integer formCode) {
			   formObjMapper.deleteObjectByFormCode( formCode);
		return formRFlowMapper.deleteObjectByFormCode(formCode);
	}
	
	/*
	 * 1、先验证flowCode是否被其他表单绑定了
	 * 2、先验证formCode和flowCode是不是一一对应的
	 * 3、然后再保存
	 */
	@Transactional
	public void saveBinding(FormRFlowDomain formRFlow){
		
		formRFlow.setId(sequenceService.getNextId("pf_form_r_flow"));
		
		FormObjDomain formObj=formObjMapper.getObjectVOById(formRFlow.getFormId());
		formRFlow.setFormCode(formObj.getFormCode());
		formRFlow.setFormName(formObj.getFormName());
		
		List flowObjList=formObjMapper.getFlowObjVOById(formRFlow.getFlowId());
		Map flowObjMap=null;
		if(flowObjList!=null && flowObjList.size()>0){
			flowObjMap=(Map) flowObjList.get(0);
			formRFlow.setFlowCode(new Integer(""+ flowObjMap.get("FLOW_CODE")));
			formRFlow.setFlowName(""+flowObjMap.get("FLOW_NAME"));
		}
		
		formRFlow.setPublishVersion(formRFlow.getId());
		formRFlow.setIsPublishVersion(new Integer(PFConstant.DIRT_IS_YES));
		

		
		Map flowMap=new HashMap();
		flowMap.put("flowCode", formRFlow.getFlowCode());
		List retGroupFlow=formRFlowMapper.getObjectMapGroupByFormCode( flowMap);
		//验证流程是不是被其他表单给绑定了
		if(retGroupFlow!=null&&retGroupFlow.size()>0){
			int formCode=formRFlow.getFormCode();
			if(retGroupFlow!=null&&retGroupFlow.size()>1){
				throw new BussinessException("",BussinessExceptionCode.F_T_PARAM_ERROR_20002);
			}else if(retGroupFlow!=null&&retGroupFlow.size()==1){
				Map objMap=(Map)retGroupFlow.get(0);
				int c=((Integer)objMap.get("form_code")).intValue();
				if(formCode!=c){
					throw new BussinessException("",BussinessExceptionCode.F_T_PARAM_ERROR_20002);
				}
			}
		}
		
		
		Map formMap=new HashMap();
		formMap.put("formCode", formRFlow.getFormCode());
		List retList=formRFlowMapper.getObjectVOList( formMap);
		if(retList!=null&&retList.size()>0){
			// 1、验证
			for(int i=0;i<retList.size();i++){
				FormRFlowDomain innerVO=(FormRFlowDomain) retList.get(i);
				if((new Integer(""+ flowObjMap.get("FLOW_CODE")))!=null && innerVO.getFlowCode()!=null && innerVO.getFlowCode().intValue()==(new Integer(""+ flowObjMap.get("FLOW_CODE"))).intValue()){
					// 两者相等时，表示目前两者还是一一对应的。----更新其他记录的版本为不是发布版本
				}else{
					throw new BussinessException("",BussinessExceptionCode.F_T_FORMCODE_NOT_MATCH_FLOWCODE);
				}
			}
			// 2、保存
			for(int i=0;i<retList.size();i++){
				FormRFlowDomain innerVO=(FormRFlowDomain) retList.get(i);
					innerVO.setIsPublishVersion(new Integer(PFConstant.DIRT_IS_NO));
					innerVO.setPublishVersion(formRFlow.getId());
					formRFlowMapper.updateObject(innerVO);
			}
			formRFlowMapper.insertObject(formRFlow);
		}else{
			formRFlowMapper.insertObject(formRFlow);
		}
	}
}