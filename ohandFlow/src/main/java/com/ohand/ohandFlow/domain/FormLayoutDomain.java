package com.ohand.ohandFlow.domain;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ohand.ohandFlow.common.BaseModel;
import com.ohand.ohandFlow.common.JsonUtil;

public class FormLayoutDomain extends BaseModel {

	private static final long serialVersionUID = 9221752564529013678L;
	
	public static final Integer LAYOUT_TYPE_DIV=new Integer(1);
	public static final Integer LAYOUT_TYPE_PORTLET=new Integer(2);
	
	private Integer id;
	
	private Integer formId;
	
	private Integer formCode;
	
	/**	1:div 2:portlet	*/
	private Integer layoutType;
	
	private String layoutUuid;
	
	private String layoutAttr;
	
	private String layoutContent;
	
	private String title;
	
	private String parentUuid;
	
	private Integer seq;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public Integer getFormCode() {
		return formCode;
	}

	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}

	public Integer getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(Integer layoutType) {
		this.layoutType = layoutType;
	}

	public String getLayoutUuid() {
		return layoutUuid;
	}

	public void setLayoutUuid(String layoutUuid) {
		this.layoutUuid = layoutUuid;
	}

	public String getLayoutAttr() {
		return layoutAttr;
	}

	public void setLayoutAttr(String layoutAttr) {
		this.layoutAttr = layoutAttr;
	}

	public String getLayoutContent() {
		return layoutContent;
	}

	public void setLayoutContent(String layoutContent) {
		this.layoutContent = layoutContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParentUuid() {
		return parentUuid;
	}

	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	public String layoutAttrWrapper(String layoutAttr){
		JSONObject jsonObj=JsonUtil.parseStr2JsonObj(layoutAttr);
		JSONArray rowsArr=(JSONArray) jsonObj.get("rows");
		if(rowsArr!=null&&rowsArr.size()>0){
			for(int i=0;i<rowsArr.size();i++){
				JSONObject jsonItem=(JSONObject)rowsArr.get(i);
				Object  editorObj=jsonItem.get("editor");
				if( editorObj instanceof JSONObject){
					JSONObject editor=(JSONObject)editorObj;
					JSONObject editorOptions=(JSONObject)editor.get("options");
					String editorType=""+editor.get("type");
					if(editorType.equals("textbox")){
						JSONArray iconsArr=(JSONArray)editorOptions.get("icons");
						JSONObject iconItem=(JSONObject)iconsArr.get(0);
						iconItem.put("handler", "function(e){websiteCompsiteObjHandler(e.data.target);}");
					}
				}
			}
		}
		String jsonStr=JsonUtil.obj2Json(jsonObj);
		jsonStr=jsonStr.replace("\"function(e){websiteCompsiteObjHandler(e.data.target);}\"", "function(e){websiteCompsiteObjHandler(e.data.target);}");
		return jsonStr;
	}
	
	public String getWebsiteCompsite(){
		JSONObject jsonObj=JsonUtil.parseStr2JsonObj(layoutAttr);
		JSONArray rowsArr=(JSONArray) jsonObj.get("rows");
		if(rowsArr!=null&&rowsArr.size()>0){
			for(int i=0;i<rowsArr.size();i++){
				JSONObject jsonItem=(JSONObject)rowsArr.get(i);
				Object  editorObj=jsonItem.get("editor");
				if( editorObj instanceof JSONObject){
					JSONObject editor=(JSONObject)editorObj;
					String editorType=""+editor.get("type");
					if(editorType.equals("textbox")){
						String  value=""+(jsonItem.get("value")==null?"":jsonItem.get("value"));
						return value;
					}
				}
			}
		}
		return "";
	}
	
	
//	public List getWebsiteCompsiteContent(PortaPageService portaPageService){
//		List retList=new ArrayList();
//		JSONObject jsonObj=JsonUtil.parseStr2JsonObj(layoutAttr);
//		JSONArray rowsArr=(JSONArray) jsonObj.get("rows");
//		String value="";
//		if(rowsArr!=null&&rowsArr.size()>0){
//			for(int i=0;i<rowsArr.size();i++){
//				JSONObject jsonItem=(JSONObject)rowsArr.get(i);
//				Object  editorObj=jsonItem.get("editor");
//				if( editorObj instanceof JSONObject){
//					JSONObject editor=(JSONObject)editorObj;
//					String editorType=""+editor.get("type");
//					if(editorType.equals("textbox")){
//						value=""+(jsonItem.get("value")==null?"":jsonItem.get("value"));
//						WebPartDomain webPartDomain=null;
//						try {
//							webPartDomain = portaPageService.getWebPartById(value);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//						if(webPartDomain!=null){
//							retList.add(webPartDomain.getJsContent()==null?"":webPartDomain.getJsContent());
//							retList.add(webPartDomain.getHtmlContent()==null?"":webPartDomain.getHtmlContent());	
//							return retList;
//						}
//					}
//				}
//			}
//		}
//		return null;
//	}
	
	
}
