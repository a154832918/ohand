package com.ohand.ohandFlow.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandFlow.domain.FormRFlowDomain;

@Mapper
public interface FormRFlowMapper extends BaseMapper<FormRFlowDomain>  {
	
	public List getObjectMapGroupByFormCode( Map map) ;
	
	public List missiveTemplate( Map map) ;
	
	public Integer deleteObjectByFormCode( Object id) ;
	
}
