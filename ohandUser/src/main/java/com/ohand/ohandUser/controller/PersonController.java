package com.ohand.ohandUser.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ohand.ohandUser.common.JsonUtil;
import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.common.ParameterHandle;
import com.ohand.ohandUser.domain.PersonDomain;
import com.ohand.ohandUser.domain.PersonImageDomain;
import com.ohand.ohandUser.domain.UserInfo;
import com.ohand.ohandUser.framework.controller.BaseController;
import com.ohand.ohandUser.service.PersonImageService;
import com.ohand.ohandUser.service.PersonService;

@Controller
public class PersonController extends BaseController {
	
	@Resource
	private transient PersonService personService;
	
	@Resource
	private transient PersonImageService personImageService;
	
	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	
	public void getObjectVOById() throws Exception{
		String id=request.getParameter("id");
		if(id==null||id.equals("")){
			initJsonResponse(response,"false",RESPONCE_TYPE_JSON);
		}else{
			PersonDomain vo=personService.getObjectVOById(new Integer(id));
			initJsonResponse(response,JsonUtil.obj2Json(vo),RESPONCE_TYPE_JSON);
		}
	}
	
	public String personEdit() throws Exception{
		String id=request.getParameter("id");
		String organizeId=request.getParameter("organizeId");
		if(organizeId==null||organizeId.equals("")){
			throw new Exception("没有传入机构Id！");
		}
		request.setAttribute("organizeId", organizeId);
		if(id==null||id.equals("")){
			return "personEdit";
		}else{
			PersonDomain vo=personService.getObjectVOById(new Integer(id));
			request.setAttribute("person", vo);
			return "personEdit";
		}
	}
	
	@RequestMapping(value="/ohand/person", method = RequestMethod.POST)
	public void saveCommon(@RequestBody PersonDomain person ) throws Exception {
		String organizeId=request.getParameter("organizeId");
		personService.insertObjectCommon(person,organizeId);
	}
	
	public void deleteObjectById() throws Exception {
		String ii=request.getParameter("id");
		Integer retNumb=0;
		if(ii.length()>0){
			retNumb=personService.deleteObjectById(new Integer(ii));
			if(retNumb>0){
				initJsonResponse(response,JsonUtil.str2Json("true"),RESPONCE_TYPE_JSON);
			}else{
				initJsonResponse(response,"false",RESPONCE_TYPE_JSON);
			}
		}else{
			initJsonResponse(response,"false",RESPONCE_TYPE_JSON);
		}
	}
	
	public void checkPersonCode() throws Exception {
		String personCode=request.getParameter("personCode");
		Map map=new HashMap();
			map.put("eqPersonCode", personCode);
		List personList=personService.getObjectVOList(map);
		if(personList!=null&&personList.size()>0){
			initJsonResponse(response,"true",RESPONCE_TYPE_JSON);
		}else{
			initJsonResponse(response,"false",RESPONCE_TYPE_JSON);
		}
	}
	
	public void updateObject(@RequestBody PersonDomain person ) throws IOException{
		Integer retNumb=personService.updateObject(person);
		if(retNumb>0){
			initJsonResponse(response,JsonUtil.str2Json("true"),RESPONCE_TYPE_JSON);
		}else{
			initJsonResponse(response,JsonUtil.str2Json("false"),RESPONCE_TYPE_JSON);
		}
	}
	
	public void search() throws Exception {
		Map paramMap = ParameterHandle.handlePage(request, PersonDomain.pageSize,
				PersonDomain.pageIndex);
		Pagination page = personService.getObjectVOListPage(paramMap);
		String json = JsonUtil.list2Json(page.getList());
		String wrapperJson = JsonUtil.wrapperJsonWithTotalCount(json,
				page.getCount());
		initJsonResponse(response, wrapperJson,RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(value="/ohand/person/_personCenter1")
	public String _personCenter1() throws Exception {
		UserInfo userInfo=getCurrUserInfo(request);
		String personId=userInfo.getPersonId()+"";
		PersonDomain vo=personService.getObjectVOById(new Integer(personId));
		request.setAttribute("person", vo);
		return "/user/person/_personCenter1";
	}
	
	@RequestMapping(value="/personInfo", method = RequestMethod.GET)
	public void personInfo() throws Exception {
		UserInfo userInfo=getCurrUserInfo(request);
		String personId=userInfo.getPersonId()+"";
		PersonDomain vo=personService.getObjectVOById(new Integer(personId));
		initJsonResponse(response, JsonUtil.obj2Json(vo),RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(value="/personImage/headerImage", method = RequestMethod.GET)
	public void getHeaderImage() throws Exception {
		UserInfo userInfo=getCurrUserInfo(request);
		String commonId=userInfo.getCommonId()+"";
		Map map=new HashMap();
			map.put("relatedCommonId", commonId);
		List retList=personImageService.getObjectVOList(map);
		if(retList!=null && retList.size()>0){
			
			PersonImageDomain vo=(PersonImageDomain)retList.get(0);
			String filePath=vo.getFilePath();
			
			File picFile=new File(filePath);
			response.setContentType("image/jpeg; charset=GBK");
			
			ServletOutputStream outputStream = response.getOutputStream();
			FileInputStream inputStream = new FileInputStream(picFile);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
			     outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			outputStream = null;
		}
		
	}
	
	
}