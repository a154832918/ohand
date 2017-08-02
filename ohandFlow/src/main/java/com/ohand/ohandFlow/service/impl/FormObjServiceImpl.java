package com.ohand.ohandFlow.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.OhandFileUtil;
import com.ohand.ohandFlow.common.PFConstant;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.common.UUIDUtil;
import com.ohand.ohandFlow.domain.FormElementDomain;
import com.ohand.ohandFlow.domain.FormElementScriptDomain;
import com.ohand.ohandFlow.domain.FormLayoutDomain;
import com.ohand.ohandFlow.domain.FormObjDomain;
import com.ohand.ohandFlow.domain.TableFieldIncrease;
import com.ohand.ohandFlow.mapper.FormElementMapper;
import com.ohand.ohandFlow.mapper.FormElementScriptMapper;
import com.ohand.ohandFlow.mapper.FormLayoutMapper;
import com.ohand.ohandFlow.mapper.FormObjMapper;
import com.ohand.ohandFlow.mapper.TableFieldIncreaseMapper;
import com.ohand.ohandFlow.service.FormObjService;
import com.ohand.ohandFlow.service.SequenceService;


@Service
public class FormObjServiceImpl implements FormObjService {
	
	@Autowired
	private FormObjMapper formObjMapper;
	
	@Autowired
	private TableFieldIncreaseMapper tableFieldIncreaseMapper;
	
	@Autowired
	private FormElementMapper formElementMapper;
	
	@Autowired
	private FormLayoutMapper formLayoutMapper;
	
	@Autowired
	private FormElementScriptMapper formElementScriptMapper ;
	
	@Autowired
	private SequenceService  sequenceService;
	
	
	public FormObjDomain getObjectVOById(Integer id) {
		return formObjMapper.getObjectVOById( id);
	}

	public List getObjectMapList(Map map) {
		return formObjMapper.getObjectMapList( map);
	}

	public List getObjectVOList(Map map) {
		return formObjMapper.getObjectVOList( map);
	}

	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = formObjMapper.getObjectMapListPagination(
				map);
		List count = (formObjMapper.getObjectMapListCount( map));
		int numb = ((Integer) count.get(0)).intValue();
		return new Pagination(pageIndex, pageSize, numb, retList);
	}
	
	@Transactional
	public void insertObjectWithStatus(FormObjDomain vo,String preFormObjId) throws IOException{
		
		Integer formCode=vo.getFormCode();
		if(formCode==null ||formCode.equals("")){
			throw new  BussinessException("传入的表单编码：唯一资源，出现差错...");
		}
		
		vo.setId(sequenceService.getNextId("pf_form_obj"));
		vo.setFormVersion(vo.getId());//设置当前版本
		vo.setCreateDate(new Date());
		
		Map map=new HashMap();
		map.put("formCode", formCode);
		List formCodeList=formObjMapper.getObjectVOList( map);
		if(formCodeList!=null && formCodeList.size()>0){//修改操作
			FormObjDomain preBiddingVO=(FormObjDomain) formCodeList.get(0);
			for(int i=0;i<formCodeList.size();i++){//1、更新pre 的最新记录的状态为 2  2、并设置最新版本
				FormObjDomain preVO=(FormObjDomain) formCodeList.get(i);
				preVO.setIsLastVersion(new Integer(PFConstant.DIRT_IS_NO));
				preVO.setLastVersion(vo.getId());
				formObjMapper.updateObject( preVO);
			}
			//设置vo的相关值
			vo.setIsLastVersion(new Integer(PFConstant.DIRT_IS_YES));
			vo.setLastVersion(vo.getId());
			vo.setBindingVersion(preBiddingVO.getBindingVersion());
		}else{// 第一次新增操作
			vo.setIsLastVersion(new Integer(PFConstant.DIRT_IS_YES));
			vo.setLastVersion(vo.getId());
			vo.setBindingVersion(null);
		}
		formObjMapper.insertObject( vo);
		
		String htmlData="";
		if(preFormObjId!=null&&!preFormObjId.equals("")&&!preFormObjId.equals("null")){
			FormObjDomain formObj=getObjectVOById(new Integer(preFormObjId));
			try {
				htmlData=OhandFileUtil.readFormDesignFile(formObj.getFormCode()+"", preFormObjId);
			} catch (IOException e) {
				e.printStackTrace();
			}
			OhandFileUtil.saveFormDesignFile(formObj.getFormCode()+"",vo.getId()+"", htmlData);
		}
		
		
		Map mapLayout=new HashMap();
			mapLayout.put("formId", preFormObjId);
		List layoutList=formLayoutMapper.getObjectVOList(mapLayout);
		if(layoutList!=null&&layoutList.size()>0){
			for(int i=0;i<layoutList.size();i++){
				FormLayoutDomain layoutDomain=(FormLayoutDomain) layoutList.get(i);
				layoutDomain.setId(sequenceService.getNextId("pf_form_layout"));
				layoutDomain.setFormId(vo.getId());
				formLayoutMapper.insertObject(layoutDomain);
			}
			String sourceDir=OhandFileUtil.OHAND_FILE_PATH + File.separator + "formfile"
					+ File.separator + vo.getFormCode()+ File.separator + preFormObjId;
			File sourceDirFile = new File(sourceDir);
			if (sourceDirFile.exists()) {
				String targetDir=OhandFileUtil.OHAND_FILE_PATH + File.separator + "formfile"
						+ File.separator + vo.getFormCode()+ File.separator + vo.getId();
				OhandFileUtil.copyDirectiory(sourceDir, targetDir);
			}
		}
		
		Map formElementScript=new HashMap();
			formElementScript.put("formId", preFormObjId);
			formElementScript.put("isLast", FormElementScriptDomain.IS_LAST_YES);
		List formElementScriptList=formElementScriptMapper.getObjectVOList(formElementScript);
		if(formElementScriptList!=null&&formElementScriptList.size()>0){
			for(int i=0;i<formElementScriptList.size();i++){
				FormElementScriptDomain formElementScriptDomain=(FormElementScriptDomain) formElementScriptList.get(i);
				String formElementScriptUuid=UUIDUtil.generateUUID();
				formElementScriptDomain.setId(formElementScriptUuid);
				formElementScriptDomain.setFormId(vo.getId());
				formElementScriptMapper.insertObject(formElementScriptDomain);
			}
		}
		
		
	}
	
	public FormObjDomain insertObject(FormObjDomain vo) {
		vo.setId(sequenceService.getNextId("pf_form_obj"));
		formObjMapper.insertObject( vo);
		return vo;
	}

	public Integer updateObject(FormObjDomain vo) {
		return formObjMapper.updateObject( vo);
	}

	public Integer deleteObjectById(Integer i) {
		return formObjMapper.deleteObjectById( i);
	}

	@Transactional
	public void saveFormFile(String formId,String htmlData,String elementObjSplits) {
		if(elementObjSplits!=null && !elementObjSplits.equals("")){
			String[] elements=elementObjSplits.split(",");
			FormObjDomain formObjDomain=formObjMapper.getObjectVOById( new Integer(formId));
			for(int i=0;i<elements.length;i++){
				String element=elements[i];
				FormElementDomain domain=new FormElementDomain();
				domain.setId(sequenceService.getNextId("pf_form_element"));
				domain.setElementCode(element);
				domain.setFormId(formObjDomain.getId());
				domain.setFormName(formObjDomain.getFormName());
				formElementMapper.insertObject(domain);
			}
			OhandFileUtil.saveFormDesignFile(formObjDomain.getFormCode()+"",formId,htmlData);
		}else{
			FormObjDomain formObjDomain=formObjMapper.getObjectVOById( new Integer(formId));
			OhandFileUtil.saveFormDesignFile(formObjDomain.getFormCode()+"",formId,htmlData);
		}
	}
	
	@Transactional
	public void savePortletFile(String formId,String portletUuid,String htmlData,String elementObjSplits) {
		if(elementObjSplits!=null && !elementObjSplits.equals("")){
			String[] elements=elementObjSplits.split(",");
			FormObjDomain formObjDomain=formObjMapper.getObjectVOById( new Integer(formId));
			for(int i=0;i<elements.length;i++){
				String element=elements[i];
				FormElementDomain domain=new FormElementDomain();
				domain.setId(sequenceService.getNextId("pf_form_element"));
				domain.setElementCode(element);
				domain.setFormId(formObjDomain.getId());
				domain.setFormName(formObjDomain.getFormName());
				formElementMapper.insertObject(domain);
			}
			OhandFileUtil.savePortletDesignFile(formObjDomain.getFormCode()+"",formObjDomain.getId()+"",portletUuid,htmlData);
		}else{
			FormObjDomain formObjDomain=formObjMapper.getObjectVOById( new Integer(formId));
			OhandFileUtil.savePortletDesignFile(formObjDomain.getFormCode()+"",formObjDomain.getId()+"",portletUuid,htmlData);
		}
	}
	
	public void saveFormInitScriptFile(String formId,String htmlData) {
		FormObjDomain formObjDomain=formObjMapper.getObjectVOById( new Integer(formId));
		OhandFileUtil.saveFormInitScriptFile(formObjDomain.getFormCode()+"",formId,htmlData);
	}
	
	public int getNextFormCode() {
		TableFieldIncrease tableFieldIncrease = new TableFieldIncrease("pf_form_obj","form_code",-1);
		synchronized (this) {
			tableFieldIncrease = (TableFieldIncrease) tableFieldIncreaseMapper.getTableFieldIncrease(tableFieldIncrease);
			if (tableFieldIncrease == null) {
				throw new RuntimeException(
						"Ohand_Error: A null sequence was returned from the database (could not get next "
								+ " pf_form_obj , form_code  get error).");
			}
			TableFieldIncrease parameterObject = new TableFieldIncrease("pf_form_obj","form_code",tableFieldIncrease.getNextVal()+1);
			tableFieldIncreaseMapper.updateTableFieldIncrease(parameterObject);
		}
		return tableFieldIncrease.getNextVal();
	}
	
	@Transactional
	public void formVersionBinding(String id){
		
		FormObjDomain obj=formObjMapper.getObjectVOById( new Integer(id));
		obj.setBindingVersion(obj.getId());
		obj.setIsBindingVersion(new Integer(PFConstant.DIRT_IS_YES));
		formObjMapper.updateObject(obj);
		
		Map map=new HashMap();
		map.put("formCode", obj.getFormCode());
		List retList=formObjMapper.getObjectVOList( map);
		if(retList!=null){
			for(int i=0;i<retList.size();i++){
				FormObjDomain innerVO=(FormObjDomain) retList.get(i);
				if(!id.equals(innerVO.getId()+"")){//除了绑定的这条记录外，其余状态都得改变
					innerVO.setBindingVersion(obj.getId());
					innerVO.setIsBindingVersion(new Integer(PFConstant.DIRT_IS_NO));
					formObjMapper.updateObject(innerVO);
				}
			}
		}
	}
}