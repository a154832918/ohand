package com.ohand.ohandUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandUser.domain.PersonRGroupDomain;

@Mapper
public interface PersonRGroupMapper extends BaseMapper<PersonRGroupDomain> {
	
	public Integer deleteObjectByGroupId(Object id);
	
}
