package com.ohand.ohandUser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.common.UUIDUtil;
import com.ohand.ohandUser.domain.PersonImageDomain;
import com.ohand.ohandUser.mapper.PersonImageMapper;
import com.ohand.ohandUser.service.PersonImageService;

@Service
public class PersonImageServiceImpl implements PersonImageService {

	@Autowired
	private PersonImageMapper personImageMapper;
	
	@Override
	public PersonImageDomain getObjectVOById(String id) {
		return personImageMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return personImageMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return personImageMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = personImageMapper.getObjectMapListPagination(map);
		List count = (personImageMapper.getObjectMapListCount(map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("numb"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public PersonImageDomain insertObject(PersonImageDomain vo) {
		String uuid=UUIDUtil.generateUUID();
		vo.setId(uuid);
		personImageMapper.insertObject( vo);
		return vo;
	}

	@Override
	public Integer updateObject(PersonImageDomain vo) {
		return personImageMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Object i) {
		return personImageMapper.deleteObjectById(i);
	}

}
