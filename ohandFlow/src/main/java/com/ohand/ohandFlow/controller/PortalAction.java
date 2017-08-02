package com.ohand.ohandFlow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ohand.ohandFlow.common.JsonUtil;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.common.ParameterHandle;
import com.ohand.ohandFlow.domain.FlowWorkItemDomain;
import com.ohand.ohandFlow.domain.UserInfo;
import com.ohand.ohandFlow.domain.fsm.StateMachineProcessor;
import com.ohand.ohandFlow.framework.controller.BaseController;

@Controller
public class PortalAction  extends BaseController {

	@Autowired
	private transient StateMachineProcessor stateMachineProcessor;
	
	public Object getModel() {
		return null;
	}
	
	/*** 公文待办--首页*/
	@RequestMapping(value="/missiveToDo/{pageSize}")
	public void  missiveToDo( String pageSize) throws IOException{
		UserInfo user=getCurrUserInfo(request);
		Map paramMap = ParameterHandle.handlePage(request, FlowWorkItemDomain.pageSize,FlowWorkItemDomain.pageIndex);
			paramMap.put("paramMap", pageSize);
		Integer[] statusArr={new Integer(0),new Integer(1)};
		paramMap.put("unCompleteWorkItem", statusArr);
		paramMap.put("receiverId", user.getCommonId()==null?-1:user.getCommonId());
		Pagination page = stateMachineProcessor.FWI_getObjectVOListPage(paramMap);
		String json = JsonUtil.list2Json(page.getList());
		String wrapperJson = JsonUtil.wrapperJsonWithTotalCount(json,
				page.getCount());
		initJsonResponse(response, wrapperJson,RESPONCE_TYPE_JSON);
	}
	

	
}
