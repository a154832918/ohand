package com.ohand.ohandUser.domain;

import java.util.Date;

import com.ohand.ohandUser.common.BaseModel;

public class PersonDomain extends BaseModel {

	private static final long serialVersionUID = 5036965392342827817L;

	private Integer id;

	private String personCode;

	private Integer gender;

	private String nickName;

	private String cardNo;

	private String chineseName;

	private String engName;

	private String oldName;

	private String nationality;

	private String zipCode;

	private Integer age;

	private Date birthday;

	private String mobile;

	private String telHome;

	private String telOffice;

	private String knowledgeCode;

	private String email;

	private String homeAddress;

	private String province;

	private String city;

	private String area;

	private String addrEdtail;

	private String presentAddress;

	private String memo;

	private Integer personStatus;

	private Integer baseFlag;

	private Integer deleteFlag;

	public PersonDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelHome() {
		return telHome;
	}

	public void setTelHome(String telHome) {
		this.telHome = telHome;
	}

	public String getTelOffice() {
		return telOffice;
	}

	public void setTelOffice(String telOffice) {
		this.telOffice = telOffice;
	}

	public String getKnowledgeCode() {
		return knowledgeCode;
	}

	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddrEdtail() {
		return addrEdtail;
	}

	public void setAddrEdtail(String addrEdtail) {
		this.addrEdtail = addrEdtail;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(Integer personStatus) {
		this.personStatus = personStatus;
	}

	public Integer getBaseFlag() {
		return baseFlag;
	}

	public void setBaseFlag(Integer baseFlag) {
		this.baseFlag = baseFlag;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
