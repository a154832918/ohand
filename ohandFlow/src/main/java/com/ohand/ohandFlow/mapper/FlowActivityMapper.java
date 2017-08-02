package com.ohand.ohandFlow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandFlow.domain.FlowActivityDomain;
@Mapper
public interface FlowActivityMapper extends BaseMapper<FlowActivityDomain> {

	public List getFlowActivityListCommon(FlowActivityDomain flowActivityDomain);

	public  void insertFlowActivityDomain(FlowActivityDomain flowActivityDomain);
	
	public Integer deleteObjectByRelatedFlowId(Object id) ;
	
}
