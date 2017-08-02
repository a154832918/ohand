package com.ohand.ohandFlow.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ohand.ohandFlow.domain.FormLayoutDomain;

@Mapper
public interface FormLayoutMapper extends BaseMapper<FormLayoutDomain>  {
	public Integer deleteObjectByLayoutUuid( Map id);
}
