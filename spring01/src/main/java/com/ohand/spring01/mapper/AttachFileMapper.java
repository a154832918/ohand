package com.ohand.spring01.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.spring01.domain.AttachFileDomain;

@Mapper
public interface AttachFileMapper extends  BaseMapper<AttachFileDomain>{
	
}