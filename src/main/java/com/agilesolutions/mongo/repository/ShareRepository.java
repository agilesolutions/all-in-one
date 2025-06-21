package com.agilesolutions.mongo.repository;

import com.agilesolutions.mongo.model.Share;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ShareRepository extends MongoRepository<Share, String> {

    @Query("{company:'?0'}")
    Share findItemByCompany(String company);

    @Query(fields="{'company' : 1, 'quantity' : 1}")
    List<Share> findAll();

    public long count();
}