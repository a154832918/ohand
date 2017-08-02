package com.ohand.ohandUser.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohand.ohandUser.domain.AttachFileDomain;

@Repository
public interface AttachFileRepository extends MongoRepository<AttachFileDomain , String> {
	
	public AttachFileDomain findById(String id);
	
	
	
}
