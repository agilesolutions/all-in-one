package com.agilesolutions.mongo.service;

import com.agilesolutions.mongo.model.Share;
import com.agilesolutions.mongo.repository.MongoDBShareRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MongoDBShareService {

    @Autowired
    private final MongoDBShareRepository shareRepository;

    public List<Share> getAllShares() {

        log.info("Get all Shares");

        return shareRepository.findAll();

    }

}
