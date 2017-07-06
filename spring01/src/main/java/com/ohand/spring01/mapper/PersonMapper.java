package com.ohand.spring01.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.spring01.domain.PersonDomain;

@Mapper
public interface PersonMapper extends BaseMapper<PersonDomain>{

	public void insertObjectCommon(PersonDomain person,String organizeId) ;
	
}