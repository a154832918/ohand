package com.ohand.ohandFlow.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ohand.ohandFlow.domain.BizConfigDomain;

@Mapper
public interface BizConfigMapper extends BaseMapper<BizConfigDomain>  {
	public List getTables(Map map);
}
