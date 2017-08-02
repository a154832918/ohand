package com.ohand.ohandUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ohand.ohandUser.domain.Sequence;
import com.ohand.ohandUser.domain.UserDomain;

@Mapper
public interface UserMapper extends BaseMapper<UserDomain>  {
	
	public void  updateSequence(Sequence parameterObject );
	
	public void insertSquence(Sequence parameterObject );
	
	public Integer getMaxId();
	
	public Sequence getSequence(Sequence parameterObject ); 
	
}
 	