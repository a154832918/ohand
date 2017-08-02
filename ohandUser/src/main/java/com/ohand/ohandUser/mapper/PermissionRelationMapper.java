package com.ohand.ohandUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandUser.domain.PermissionRelationDomain;
@Mapper
public interface PermissionRelationMapper extends BaseMapper<PermissionRelationDomain>  {

	public Integer deleteObjectByPermissionItemId(String mapperName, Object id);
	
}
