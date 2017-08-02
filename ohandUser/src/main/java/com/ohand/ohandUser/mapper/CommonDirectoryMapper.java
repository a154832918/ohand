package com.ohand.ohandUser.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandUser.domain.CommonDirectoryDomain;

@Mapper
public interface CommonDirectoryMapper extends BaseMapper<CommonDirectoryDomain> {
	
	public List getObjectVOListTreePagination(String mapperName, Map map);
	
	public List getObjectVOListTreeCount(String mapperName, Map map) ;
	
}
