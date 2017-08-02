package com.ohand.ohandUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandUser.domain.OrganizeRPersonDomain;

@Mapper
public interface OrganizeRPersonMapper extends BaseMapper<OrganizeRPersonDomain> {
	
	public Integer deleteObjectByPersonId(Object id) ;
	
	public Integer deleteObjectByOrganizeId(Object id);
	
}
