package com.ohand.ohandUser.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ohand.ohandUser.domain.PermissionItemDomain;

@Mapper
public interface PermissionItemMapper extends BaseMapper<PermissionItemDomain>  {
	
	public List getObjectVOListTreePagination( Map map) ;

	public List getObjectVOListTreeCount( Map map);
	
}
