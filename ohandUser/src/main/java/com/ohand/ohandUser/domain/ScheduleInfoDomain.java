package com.ohand.ohandUser.domain;

import java.io.Serializable;
import java.util.Date;

import com.ohand.ohandUser.common.BaseModel;

public class ScheduleInfoDomain extends BaseModel{
	
	private static final long serialVersionUID = -1360877731987575393L;

	private Integer id;

	private String scheduleName;

	private Date deadline;

	private Date remindTime;

	private String businessId;

	private String scheduleType;
	
	/**显示状态：会议、外出、休假、加班*/
	private Integer emergencyDegree;

	private String createDepart;

	private Integer createDepartId;

	private Integer userId;

	private Integer completion;

	private String contentUrl;

	private String remark;

	public ScheduleInfoDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public Integer getEmergencyDegree() {
		return emergencyDegree;
	}

	public void setEmergencyDegree(Integer emergencyDegree) {
		this.emergencyDegree = emergencyDegree;
	}

	public String getCreateDepart() {
		return createDepart;
	}

	public void setCreateDepart(String createDepart) {
		this.createDepart = createDepart;
	}

	public Integer getCreateDepartId() {
		return createDepartId;
	}

	public void setCreateDepartId(Integer createDepartId) {
		this.createDepartId = createDepartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getCompletion() {
		return completion;
	}

	public void setCompletion(Integer completion) {
		this.completion = completion;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
