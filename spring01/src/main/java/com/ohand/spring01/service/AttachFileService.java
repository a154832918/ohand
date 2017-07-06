package com.ohand.spring01.service;

import java.util.List;
import java.util.Map;

import com.ohand.spring01.common.Pagination;
import com.ohand.spring01.domain.AttachFileDomain;

public interface  AttachFileService {
	
	public AttachFileDomain getObjectVOById(String id) ;
	
	public AttachFileDomain getObjectVOFileUuid(String fileUuid);
	
	public List getObjectMapList(Map map);

	public List getObjectVOList(Map map);

	public Pagination getObjectVOListPage(Map map);
	
	public AttachFileDomain save(AttachFileDomain vo);

	public Integer deleteObjectById(Integer i);

}
