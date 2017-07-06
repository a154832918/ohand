package com.ohand.spring01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.spring01.domain.PersonDomain;
import com.ohand.spring01.mapper.PersonMapper;
import com.ohand.spring01.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonMapper personMapper;
	
	public PersonDomain getObjectVOById(Integer id){
		return personMapper.getObjectVOById(id);
	}
	
	
	
}
