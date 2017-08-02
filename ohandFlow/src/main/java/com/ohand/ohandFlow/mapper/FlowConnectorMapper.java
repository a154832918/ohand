package com.ohand.ohandFlow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandFlow.domain.FlowConnectorDomain;
@Mapper
public interface FlowConnectorMapper extends BaseMapper<FlowConnectorDomain> {

	public List getFlowConnectorListCommon(FlowConnectorDomain flowConnector) ;

	public void insertFlowConnectorDomain(FlowConnectorDomain flowConnector) ;
	
	public Integer deleteObjectByRelatedFlowId(String mapperName, Object id) ;
	
}
