package com.ohand.ohandFlow.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandFlow.domain.TableFieldIncrease;

@Mapper
public interface TableFieldIncreaseMapper {

	public TableFieldIncrease getTableFieldIncrease(TableFieldIncrease tableFieldIncrease);

	public void updateTableFieldIncrease(TableFieldIncrease tableFieldIncrease);
	
}
