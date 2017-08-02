package com.ohand.ohandUser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandUser.domain.Sequence;
import com.ohand.ohandUser.mapper.UserMapper;
import com.ohand.ohandUser.service.SequenceService;

@Service
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public Integer getNextId(String tableName){
		Sequence sequence = new Sequence(tableName, -1);
		synchronized (this) {
			sequence = (Sequence) userMapper.getSequence(sequence);
			if (sequence == null) {
				Integer maxId=userMapper.getMaxId()==null?0:userMapper.getMaxId();
				sequence=new Sequence();
				sequence.setId(maxId+1);
				sequence.setName(tableName);
				sequence.setNextId(1);
				userMapper.insertSquence(sequence);
			}
			Sequence parameterObject = new Sequence(tableName,sequence.getNextId() + 1);
			userMapper.updateSequence(parameterObject);
		}
		return sequence.getNextId();
	}

}
