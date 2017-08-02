package com.ohand.ohandFlow.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ohand.ohandFlow.domain.FlowObjDomain;
import com.ohand.ohandFlow.domain.Sequence;

@Mapper
public interface FlowObjMapper extends BaseMapper<FlowObjDomain> {
	
	public List getMaxNextFlowCode();
	public Integer createTable(FlowObjDomain vo);
	public Integer isExistsTableName(Map  map);
	public Integer deleteObjectByFlowCode(Integer id) ;
	
	
	public void  updateSequence(Sequence parameterObject );
	public void insertSquence(Sequence parameterObject );
	public Integer getMaxId();
	public Sequence getSequence(Sequence parameterObject ); 
	
}
