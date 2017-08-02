package com.ohand.ohandFlow.domain;

import com.ohand.ohandFlow.common.BaseModel;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-10-28
 */
public class FlowActivityDomain extends BaseModel {
	
	private static final long serialVersionUID = -4635128501454513556L;
	public static final Integer FLOW_ITEM_TYPE_StartPointActivity = new Integer(0);
	public static final Integer FLOW_ITEM_TYPE_NormalActivity = new Integer(1);
	public static final Integer FLOW_ITEM_TYPE_SerialActivity= new Integer(2);
	public static final Integer FLOW_ITEM_TYPE_EncyclicActivity = new Integer(3);
	public static final Integer FLOW_ITEM_TYPE_SubFlowActivity = new Integer(4);
	public static final Integer FLOW_ITEM_TYPE_DummyActivity = new Integer(5);
	public static final Integer FLOW_ITEM_TYPE_EndPointActivity = new Integer(6);
	public static final Integer FLOW_ITEM_TYPE_BizAcitivity = new Integer(7);
	public static final Integer FLOW_ITEM_TYPE_TimerTask = new Integer(8);
	public static final Integer FLOW_ITEM_TYPE_TimerNoTask= new Integer(9);
	
	public static final String SCRIPT_TYPE_flowExecFilter="flowExecFilterHandler";
	public static final String SCRIPT_TYPE_flowExecBefore="flowExecBeforeHandler";
	public static final String SCRIPT_TYPE_flowExecAfter="flowExecAfterHandler";
	
	public static Integer name2Id(String name) {
		if (name == null) {
			return FLOW_ITEM_TYPE_NormalActivity;
		} else if (name.equals("StartPointActivity")) {
			return FLOW_ITEM_TYPE_StartPointActivity;
		} else if (name.equals("NormalActivity")) {
			return FLOW_ITEM_TYPE_NormalActivity;
		} else if (name.equals("SerialActivity")) {
			return FLOW_ITEM_TYPE_SerialActivity;
		} else if (name.equals("EncyclicActivity")) {
			return FLOW_ITEM_TYPE_EncyclicActivity;
		} else if (name.equals("SubFlowActivity")) {
			return FLOW_ITEM_TYPE_SubFlowActivity;
		} else if (name.equals("DummyActivity")) {
			return FLOW_ITEM_TYPE_DummyActivity;
		} else if (name.equals("EndPointActivity")) {
			return FLOW_ITEM_TYPE_EndPointActivity;
		} else if (name.equals("BizAcitivity")) {
			return FLOW_ITEM_TYPE_BizAcitivity;
		} else if (name.equals("TimerTask")) {
			return FLOW_ITEM_TYPE_TimerTask;
		} else if (name.equals("TimerNoTask")) {
			return FLOW_ITEM_TYPE_TimerNoTask;
		}
		return FLOW_ITEM_TYPE_NormalActivity;
	}

	public static String id2Name(Integer i) {
		switch (i) {
		case 0:
			return "StartPointActivity";
		case 1:
			return "NormalActivity";
		case 2:
			return "SerialActivity";
		case 3:
			return "EncyclicActivity";
		case 4:
			return "SubFlowActivity";
		case 5:
			return "DummyActivity";
		case 6:
			return "EndPointActivity";
		case 7:
			return "BizAcitivity";	
		case 8:
			return "TimerTask";
		case 9:
			return "TimerNoTask";			
		default:
			return "NormalActivity";
		}
	}

	private Integer id;

	private String activityName;

	private Integer activityType;

	private String activityCode;

	private String showAttr;

	private String activityDes;

	private Integer relatedFlowId;

	private String relatedFlowName;

	private Integer relatedExecuteId;

	private String scriptPath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRelatedFlowId() {
		return relatedFlowId;
	}

	public void setRelatedFlowId(Integer relatedFlowId) {
		this.relatedFlowId = relatedFlowId;
	}

	public String getRelatedFlowName() {
		return relatedFlowName;
	}

	public void setRelatedFlowName(String relatedFlowName) {
		this.relatedFlowName = relatedFlowName;
	}

	public Integer getRelatedExecuteId() {
		return relatedExecuteId;
	}

	public void setRelatedExecuteId(Integer relatedExecuteId) {
		this.relatedExecuteId = relatedExecuteId;
	}

	public String getScriptPath() {
		return scriptPath;
	}

	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}

	public String getShowAttr() {
		return showAttr;
	}

	public void setShowAttr(String showAttr) {
		this.showAttr = showAttr;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityDes() {
		return activityDes;
	}

	public void setActivityDes(String activityDes) {
		this.activityDes = activityDes;
	}
}
