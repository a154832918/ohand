package com.ohand.ohandUser.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ohand.ohandUser.common.JsonUtil;
import com.ohand.ohandUser.common.PFConstant;
import com.ohand.ohandUser.common.PortalUtil;
import com.ohand.ohandUser.domain.ResourceDomain;
import com.ohand.ohandUser.domain.UserInfo;
import com.ohand.ohandUser.domain.tree.ResourceTreeGridVO;
import com.ohand.ohandUser.framework.controller.BaseController;
import com.ohand.ohandUser.service.PersonRRoleService;
import com.ohand.ohandUser.service.ResourceService;
import com.ohand.ohandUser.service.RoleRResourceService;
import org.springframework.ui.Model;

@Controller
public class PortalAction  extends BaseController {
	
	@Resource
	private transient ResourceService resourceService ;
	@Resource
	private transient PersonRRoleService personRRoleService;
	@Resource
	private transient RoleRResourceService roleRResourceService;
	
	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public PersonRRoleService getPersonRRoleService() {
		return personRRoleService;
	}

	public void setPersonRRoleService(PersonRRoleService personRRoleService) {
		this.personRRoleService = personRRoleService;
	}

	public RoleRResourceService getRoleRResourceService() {
		return roleRResourceService;
	}

	public void setRoleRResourceService(RoleRResourceService roleRResourceService) {
		this.roleRResourceService = roleRResourceService;
	}

	public Object getModel() {
		return null;
	}
	
	@RequestMapping(value="/portal/home")
	public String  home(Model model){
		
		Map map=new HashMap();
			map.put("enableFlag", PFConstant.DIRT_IS_YES);
			map.put("sysCode", "oa");
		List list=resourceService.getObjectVOList(map);
		
		UserInfo user=getCurrUserInfo(request);
		Integer personId=user.getPersonId();
		Map roleMap=new HashMap();
		roleMap.put("personId", personId);
		List personRRoleList=personRRoleService.getObjectVOList(roleMap);
		// 1、查询角色  2、查询栏目集合
		if(personRRoleList==null || personRRoleList.size()==0){
			list=new ArrayList();
		}else{
			list=PortalUtil.menuFilterByRole(roleRResourceService,list,personRRoleList);
		}
		
		List retList=PortalUtil.buildTreeJson(list, 0);
		
		model.addAttribute("resourceList", retList);
		model.addAttribute("userInfo", user);
		
		String skinParam=request.getParameter("skinParam");
		if(skinParam!=null&&skinParam.equals("bootstrap")){
			return "/" +
					"/home_bootstrap";
		}else{
			return "/website/homeClient";
		}
		
	}

	
	public String  homeClient(){
		
		Map map=new HashMap();
			map.put("enableFlag", PFConstant.DIRT_IS_YES);
			map.put("sysCode", "oa");
			map.put("isShow2Client", PFConstant.DIRT_IS_YES);
			
		List list=resourceService.getObjectVOList(map);
		
		UserInfo user=getCurrUserInfo(request);
		Integer personId=user.getPersonId();
		Map roleMap=new HashMap();
		roleMap.put("personId", personId);
		List personRRoleList=personRRoleService.getObjectVOList(roleMap);
		// 1、查询角色  2、查询栏目集合
		if(personRRoleList==null || personRRoleList.size()==0){
			list=new ArrayList();
		}else{
			list=PortalUtil.menuFilterByRole(roleRResourceService,list,personRRoleList);
		}
		
		request.setAttribute("resourceList", list);
		request.setAttribute("userInfo", user);
		
		return "homeClient";
		
	}
	
	public void  navControl() throws IOException{
		String column=request.getParameter("column");
		if(column!=null&&!column.equals("")){
			ResourceDomain resource=resourceService.getObjectVOById(new Integer(column));
			String redirect= resource.getRedirectPath();
			initJsonResponse(response, JsonUtil.str2Json(""+redirect),RESPONCE_TYPE_JSON);
		}else{
			
		}
	}
	
	/*** 公文待办--首页*/
	@RequestMapping(value="/desktop/missiveToDo/{pageSize}")
	public void  missiveToDo( String pageSize) throws IOException{
//		UserInfo user=getCurrUserInfo(request);
//		Map paramMap = ParameterHandle.handlePage(request, FlowWorkItemDomain.pageSize,FlowWorkItemDomain.pageIndex);
//			paramMap.put("paramMap", pageSize);
//		Integer[] statusArr={new Integer(0),new Integer(1)};
//		paramMap.put("unCompleteWorkItem", statusArr);
//		paramMap.put("receiverId", user.getCommonId()==null?-1:user.getCommonId());
//		Pagination page = stateMachineProcessor.FWI_getObjectVOListPage(paramMap);
//		String json = JsonUtil.list2Json(page.getList());
//		String wrapperJson = JsonUtil.wrapperJsonWithTotalCount(json,
//				page.getCount());
//		initJsonResponse(response, wrapperJson,RESPONCE_TYPE_JSON);
	}
	
	/*** 左边菜单栏* @throws IOException */
	@RequestMapping(value="/portal/leftFrame")
	public void  leftFrame() throws IOException{
		
		UserInfo user=getCurrUserInfo(request);
		Integer personId=user.getPersonId();
		
		StringBuffer sb=new StringBuffer();
		String topId=request.getParameter("topId");
		
		List listMenu=resourceService.fetchSortedMenuByTopId(topId+"");
		
		Map roleMap=new HashMap();
			roleMap.put("personId", personId);
		List personRRoleList=personRRoleService.getObjectVOList(roleMap);
		
		listMenu=PortalUtil.menuFilterByRole(roleRResourceService,listMenu,personRRoleList);
		
		sb.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"outlookBar normalBar\" id=\"outlookBar\">");
		if(listMenu!=null && listMenu.size()>0){
			for(int i=0;i<listMenu.size();i++){
				ResourceTreeGridVO vo=(ResourceTreeGridVO) listMenu.get(i);
				sb.append("<tr>");
				sb.append("<td class=\"outlookItemTd\">");
				sb.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"outlookItem_active\" id=\"outlookItem"+(i+1)+"\">");	
				sb.append("<tr class=\"outlookHead\">");
					sb.append("<td><span class=\"outlookSwitch\"></span>");			
					sb.append("<span class=\"outlookIcon iconReaded\"></span>");					
					sb.append("<span class=\"outlookTitle\">"+vo.getName()+"</span></td>");
				sb.append("</tr>");
				sb.append("<tr class=\"outlookBody\">");
				sb.append("<td>");
				sb.append("<div class=\"outlookCont\">");	
				sb.append("<ul class=\"\">");
					if(vo.getChildren()!=null && vo.getChildren().size()>0){
						for(int j=0;j<vo.getChildren().size();j++){
							ResourceTreeGridVO vo3=(ResourceTreeGridVO) vo.getChildren().get(j);
							sb.append("<li  thisMenuId=\""+vo3.getId()+"\" class=\"fa-home\" id=\"infoItem_"+vo3.getId()+"\" href=\""+vo3.getRedirectPath() +"\" isNewPage="+vo3.getIsNewPage()+" isModal="+vo3.getIsModal()+"	> <a href='#'><i class='fa fa-home'></i>"+vo3.getName()+"</a></li>");
						}
					}
				sb.append("</ul>");
				sb.append("</div>");
				sb.append("</td>");
				sb.append("</tr>");					
				sb.append("</table>");					
				sb.append("</td>");					
				sb.append("</tr>");					
			}
		}
		sb.append("</table>");	
		initJsonResponse(response, JsonUtil.str2Json(""+sb),RESPONCE_TYPE_JSON);
	}
	
	public void accessMenuLog() throws Exception{
//		String sysCode=request.getParameter("sysCode");
//		String menuId=request.getParameter("menuId");
//		ResourceDomain menu=resourceService.getObjectVOById(new Integer(menuId));
//		UserInfo user=getCurrUserInfo(request);
//		OnlineUser olUser = new OnlineUser(user);
//		LogServiceImpl logService = LogBeanFactory.getLogService();
//		AccessLogVO log = new AccessLogVO(olUser);
//		log.setResType(AccessLogVO.RESTYPE_MENU);
//		log.setResNo(sysCode+"#"+menu.getId());
//		log.setResName(menu.getName());
//		logService.createAccessLog(log);
	}

	
}
