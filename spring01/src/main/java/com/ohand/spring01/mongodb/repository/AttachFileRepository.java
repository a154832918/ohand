package com.ohand.spring01.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohand.spring01.domain.AttachFileDomain;

@Repository
public interface AttachFileRepository extends MongoRepository<AttachFileDomain , String> {
	
	public AttachFileDomain findById(String id);
	
	
	
}
