package com.ohand.ohandUser.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandUser.domain.CommonDomain;

@Mapper
public interface CommonMapper extends BaseMapper<CommonDomain> {
	
	public Integer updateObjectName(Map map);
	
	public Integer deleteObjectByRecordIdAndParentId( Object id);
	
	public Integer deleteObjectByParentIdAndSourceType( Object id);
	
	public Integer deleteObjectByRecordId(Object id);
	
}
