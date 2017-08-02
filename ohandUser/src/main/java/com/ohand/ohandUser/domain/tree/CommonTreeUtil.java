package com.ohand.ohandUser.domain.tree;

import java.util.ArrayList;
import java.util.List;

import com.ohand.ohandUser.domain.CommonDomain;

public class CommonTreeUtil {
	private static CommonTreeUtil commonTreeUtil=null;
	public static CommonTreeUtil getInstance(){
		if(commonTreeUtil==null){
			commonTreeUtil=new CommonTreeUtil();
		}
		return commonTreeUtil;
	}
	private CommonTreeUtil(){
		
	}
	//地址本，根据数据类型判断树iconCls的属性
	public List<CommonTreeModel> commonConvertList(List<CommonTreeModel> list, int parentId) {
		List<CommonTreeModel> resultList = new ArrayList<CommonTreeModel>();
		for (CommonTreeModel tree : list) {
			if (tree.getTreeParentId() != null && tree.getTreeParentId() == parentId) {
				List<CommonTreeModel> childList=commonConvertList(list, tree.getTreeId().intValue());
				//设置子节点
				if (childList != null && childList.size() > 0) {
					tree.setChildrenValue(childList);
					if(childList==null||childList.size()==0){
						tree.setStateValue("open");
					}else{
						tree.setStateValue("closed");
					}
				}
				//判断节点iconCls
				if(tree.getCommon()!=null&&tree.getCommon().getSourceType()!=null){
					Integer sourceType=tree.getCommon().getSourceType();
					switch (sourceType) {
						case 1:
							tree.setIconCls("icon-user8");
							tree.setState("open");
							break;
						case 2:
							tree.setIconCls("icon-dept");
							break;
						case 3:
							tree.setIconCls("icon-group");
							break;
						case 4:
							tree.setIconCls("icon-role");
							break;
						default:
					}
				}
				if(tree.getCommon()!=null&&tree.getCommon().getRecordId()!=null){
					CommonTreeAttribute  cta=new CommonTreeAttribute();
					cta.setRecordId(tree.getCommon().getRecordId());
					cta.setCommonParentId(tree.getCommon().getParentId());
					tree.setAttributes(cta);
				}
				tree.setId(tree.getCommon().getSysbol());
				resultList.add(tree);
			} 
		}
		return resultList;
	}
	
	/** 地址本，根据数据类型判断树iconCls的属性*/
	public List<CommonTreeModel> commonConvertList(List<CommonTreeModel> list) {
		List<CommonTreeModel> resultList = new ArrayList<CommonTreeModel>();
		for (CommonTreeModel tree : list) {
				List<CommonTreeModel> childList=commonConvertList(list, tree.getTreeId().intValue());
				//设置子节点
				if (childList != null && childList.size() > 0) {
					tree.setChildrenValue(childList);
					if(childList==null||childList.size()==0){
						tree.setStateValue("open");
					}else{
						tree.setStateValue("closed");
					}
				}
				//判断节点iconCls
				if(tree.getCommon()!=null&&tree.getCommon().getSourceType()!=null){
					Integer sourceType=tree.getCommon().getSourceType();
					switch (sourceType) {
						case 1:
							tree.setIconCls("icon-user8");
							tree.setState("open");
							break;
						case 2:
							tree.setIconCls("icon-dept");
							break;
						case 3:
							tree.setIconCls("tree-folder");
							break;
						case 4:
							tree.setIconCls("tree-folder");
							break;
						default:

					}
				}
				if(tree.getCommon()!=null&&tree.getCommon().getRecordId()!=null){
					CommonTreeAttribute  cta=new CommonTreeAttribute();
					cta.setRecordId(tree.getCommon().getRecordId());
					cta.setCommonParentId(tree.getCommon().getParentId());
					tree.setAttributes(cta);
				}
				tree.setId(tree.getCommon().getSysbol());
				resultList.add(tree);
		}
		return resultList;
	}
	
	/** 树的扩展属性 */
	class CommonTreeAttribute{
		/**关联表记录Id*/
		private Integer recordId;
		/**本表记录的父记录*/
		private Integer commonParentId;

		public Integer getRecordId() {
			return recordId;
		}
		public void setRecordId(Integer recordId) {
			this.recordId = recordId;
		}
		public Integer getCommonParentId() {
			return commonParentId;
		}
		public void setCommonParentId(Integer commonParentId) {
			this.commonParentId = commonParentId;
		}
	}
	
	/**已选择右边tree展示*/
	public List convertSelectedCommon(List list){
		if(list==null){
			return null;
		}
		List<CommonTreeModel> treeModels = new ArrayList<CommonTreeModel>();
		for(int i=(list.size()-1);i>=0;i--){
			CommonDomain common =(CommonDomain)list.get(i);			
			CommonTreeModel treeModel = new CommonTreeModel(common);
			treeModel.setState("open");
			treeModels.add(treeModel);
	   }
	   for (CommonTreeModel tree : treeModels) {
				//判断节点iconCls
				if(tree.getCommon()!=null&&tree.getCommon().getSourceType()!=null){
					Integer sourceType=tree.getCommon().getSourceType();
					switch (sourceType) {
						case 1:
							tree.setIconCls("icon-user8");
							tree.setState("open");
							break;
						case 2:
							tree.setIconCls("icon-dept");
							break;
						case 3:
							tree.setIconCls("tree-folder");
							break;
						case 4:
							tree.setIconCls("tree-folder");
							break;
						default:

					}
				}
				if(tree.getCommon()!=null&&tree.getCommon().getRecordId()!=null){
					CommonTreeAttribute  cta=new CommonTreeAttribute();
					cta.setRecordId(tree.getCommon().getRecordId());
					cta.setCommonParentId(tree.getCommon().getParentId());
					tree.setAttributes(cta);
				}
				tree.setId(tree.getCommon().getSysbol());
		}
	  return  (treeModels);
	}
	
}