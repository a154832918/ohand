package com.ohand.ohandFlow.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ohand.ohandFlow.domain.FlowCategoryDomain;

@Mapper
public interface FlowCategoryMapper extends BaseMapper<FlowCategoryDomain> {
	
	public List getObjectVOListTreePagination(Map map);
	
	public List getObjectVOListTreeCount(Map map) ;
}
