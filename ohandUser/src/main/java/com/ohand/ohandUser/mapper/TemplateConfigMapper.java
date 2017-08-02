package com.ohand.ohandUser.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ohand.ohandUser.domain.TemplateConfigDomain;

@Mapper
public interface TemplateConfigMapper extends BaseMapper<TemplateConfigDomain>  {

}
