package com.ohand.ohandUser.service;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.PersonImageDomain;

public interface PersonImageService {
	
	public PersonImageDomain getObjectVOById(String id);

	public List getObjectMapList(Map map) ;

	public List getObjectVOList(Map map);

	public Pagination getObjectVOListPage(Map map);

	public PersonImageDomain insertObject(PersonImageDomain vo);

	public Integer updateObject(PersonImageDomain vo);

	public Integer deleteObjectById(Object i);

}
