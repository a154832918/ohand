package com.ohand.ohandUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandUser.domain.PersonRRoleDomain;

@Mapper
public interface PersonRRoleMapper extends BaseMapper<PersonRRoleDomain>  {
	
	public Integer deleteObjectByRoleId(Object id);
	
}
