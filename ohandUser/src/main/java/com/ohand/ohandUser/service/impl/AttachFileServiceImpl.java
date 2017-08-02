package com.ohand.ohandUser.service.impl;

import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.common.UUIDUtil;
import com.ohand.ohandUser.domain.AttachFileDomain;
import com.ohand.ohandUser.mapper.AttachFileMapper;
import com.ohand.ohandUser.service.AttachFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachFileServiceImpl implements  AttachFileService {
	
	@Autowired
	private AttachFileMapper attachFileMapper;
	
	public AttachFileDomain getObjectVOById(String id) {
		return attachFileMapper.getObjectVOById(id);
	}
	
	public AttachFileDomain getObjectVOFileUuid(String fileUuid) {
//		return attachFileMapper.getObjectVOFileUuid(fileUuid);
		return null;
	}
	
	public List getObjectMapList(Map map) {
		return attachFileMapper.getObjectMapList(map);
	}

	public List getObjectVOList(Map map) {
		return attachFileMapper.getObjectVOList(map);
	}

	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList =null;// attachFileMapper.getObjectMapListPagination(map);
		List count = null;//(attachFileMapper.getObjectMapListCount(map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}
	
	public AttachFileDomain save(AttachFileDomain vo) {
		if(vo.getId()==null || vo.equals("")){
			String uuid=UUIDUtil.generateUUID();
			vo.setId(uuid);
			attachFileMapper.insertObject(vo);
			return vo;
		}else{
			attachFileMapper.updateObject(vo);
			return vo;
		}
	}

	public Integer deleteObjectById(Integer i) {
		return attachFileMapper.deleteObjectById(i);
	}

}
