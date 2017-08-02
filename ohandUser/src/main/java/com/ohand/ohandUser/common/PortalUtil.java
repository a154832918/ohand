package com.ohand.ohandUser.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.domain.PersonRRoleDomain;
import com.ohand.ohandUser.domain.ResourceDomain;
import com.ohand.ohandUser.domain.tree.ResourceTreeGridVO;
import com.ohand.ohandUser.service.RoleRResourceService;

public class PortalUtil {


	public static List buildTreeJson(List list, int parentId ) {
		PortalUtil util = new PortalUtil();
		List sortList = util.buildTreeList(list, parentId);
		return sortList;
	}
		
	public List buildTreeList(List list, int parentId) {
		List<ResourceDomain> result = new ArrayList<ResourceDomain>();
		for (int i = 0; i < list.size(); i++) {
			ResourceTreeGridVO domain = (ResourceTreeGridVO) list.get(i);
			if (domain.getParentId() != null
					&& domain.getParentId().intValue() == parentId) {
				List childList = buildTreeList(list, domain.getId()
						.intValue());
				if (childList != null && childList.size() > 0) {
					domain.setChildren(childList);
					domain.setChildrenCount(new Integer(childList.size()));
				}
				result.add(domain);
			}
		}
		return result;
	}
	
	public static List menuFilterByRole(RoleRResourceService roleRResourceService,List list,List personRRoleList){
		Map roleRResourceMap=new HashMap();
		Integer[] roleIds=null;
		roleIds=new Integer[personRRoleList.size()];
		for(int i=0;i<personRRoleList.size();i++){
			PersonRRoleDomain domain=(PersonRRoleDomain)personRRoleList.get(i);
			roleIds[i]=domain.getRoleId();
		}
		if(roleIds.length==0){
			roleIds=new Integer[1];
			roleIds[0]=-1;
		}
		roleRResourceMap.put("roleIds", roleIds);
		List roleRResourceList=roleRResourceService.getObjectVOList(roleRResourceMap);
//		for(int i=(list.size()-1);i>=0;i--){
//			ResourceDomain resourceDomain=(ResourceDomain) list.get(i);
//			boolean isExist=false;
//			for(int j=0;j<roleRResourceList.size();j++){
//				RoleRResourceDomain vo=(RoleRResourceDomain) roleRResourceList.get(j);
//				if(resourceDomain.getId().intValue()==vo.getResourceId().intValue()){
//					isExist=true;
//					break;
//				}
//			}
//			if(isExist==false){
//				list.remove(i);
//			}
//		}
		return list;
	}
	
}
