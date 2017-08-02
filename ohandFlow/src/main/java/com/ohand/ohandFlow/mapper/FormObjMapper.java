package com.ohand.ohandFlow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ohand.ohandFlow.domain.FormObjDomain;

@Mapper
public interface FormObjMapper extends BaseMapper<FormObjDomain> {
	
	public List getFlowObjVOById(Integer id);
	
	public Integer deleteObjectByFormCode(Object id) ;
	
}
