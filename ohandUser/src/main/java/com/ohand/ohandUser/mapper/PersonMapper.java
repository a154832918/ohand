package com.ohand.ohandUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandUser.domain.PersonDomain;

@Mapper
public interface PersonMapper extends BaseMapper<PersonDomain>{

	public void insertObjectCommon(PersonDomain person,String organizeId) ;
	
}