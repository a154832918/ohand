package com.ohand.ohandFlow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohand.ohandFlow.domain.Sequence;
import com.ohand.ohandFlow.mapper.FlowObjMapper;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	private FlowObjMapper flowObjMapper;
	
	@Transactional
	public Integer getNextId(String tableName){
		Sequence sequence = new Sequence(tableName, -1);
		synchronized (this) {
			sequence = (Sequence) flowObjMapper.getSequence(sequence);
			if (sequence == null) {
				Integer maxId=flowObjMapper.getMaxId()==null?0:flowObjMapper.getMaxId();
				sequence=new Sequence();
				sequence.setId(maxId+1);
				sequence.setName(tableName);
				sequence.setNextId(1);
				flowObjMapper.insertSquence(sequence);
			}
			Sequence parameterObject = new Sequence(tableName,sequence.getNextId() + 1);
			flowObjMapper.updateSequence(parameterObject);
		}
		return sequence.getNextId();
	}

}
