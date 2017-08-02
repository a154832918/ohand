package com.ohand.ohandUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandUser.common.MD5Util;
import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.UserDomain;
import com.ohand.ohandUser.mapper.UserMapper;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public UserDomain getObjectVOById(Integer id) {
		return userMapper.getObjectVOById(id);
	}

	@Override
	public UserDomain getUserByAccount(String account) {
		Map map=new HashMap();
		map.put("account", account);
		List ret=userMapper.getObjectVOList(map);
		if(ret!=null&&ret.size()==1){
			return (UserDomain)ret.get(0);
		}else{
			throw new BussinessException("UserServiceImpl.getUserByAccount获取数据异常！");
		}
	}

	@Override
	public UserDomain findUserByThirdId(String thirdId) {
		Map map=new HashMap();
		map.put("account", thirdId);
		map.put("loginType", UserDomain.loginType_OAuth2);
		List ret=userMapper.getObjectVOList(map);
		if(ret!=null&&ret.size()==1){
			return (UserDomain)ret.get(0);
		}else{
			throw new BussinessException("UserServiceImpl.findUserByThirdId获取数据异常！");
		}
	}

	@Override
	public List getObjectMapList(Map map) {
		return userMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return userMapper.getObjectVOList(map);
	}
	
	@Override
	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = userMapper.getObjectMapListPagination(map);
		List count = (userMapper.getObjectMapListCount(map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public UserDomain insertObject(UserDomain vo) {
		vo.setId(sequenceService.getNextId("pf_user"));
		vo.setPassword(MD5Util.getMD5String(vo.getPassword()+""));
		userMapper.insertObject(vo);
		return vo;
	}
	
	@Override
	public Integer updateObject(UserDomain vo) {
		vo.setPassword(MD5Util.getMD5String(vo.getPassword()+""));
		return userMapper.updateObject(vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return userMapper.deleteObjectById(i);
	}

}
