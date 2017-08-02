package com.ohand.ohandUser.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandUser.domain.ConstantConfigDomain;

@Mapper
public interface ConstantConfigMapper extends BaseMapper<ConstantConfigDomain> {
	
	public List getObjectVOListTreePagination(Map map);
	
	public List getObjectVOListTreeCount(Map map) ;
	
}
