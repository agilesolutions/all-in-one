package com.agilesolutions.mongo.service;

import com.agilesolutions.config.ApplicationProperties;
import com.agilesolutions.config.JunitConfig;
import com.agilesolutions.config.SpringMongoConfig;
import com.agilesolutions.controller.StockController;
import com.agilesolutions.exception.CustomControllerAdvice;
import com.agilesolutions.mongo.base.BaseIntegrationTest;
import com.agilesolutions.mongo.model.Share;
import com.agilesolutions.mongo.repository.ShareRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataMongoTest
@ContextConfiguration(classes = {ShareRepository.class, SpringMongoConfig.class})
class ShareServiceTest extends BaseIntegrationTest {


    @Autowired
    ShareRepository shareRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void givenProductRepository_whenSaveAndRetrieveProduct_thenOK() {

        List<Share> shares = shareRepository.findAll();

        assertAll("check all",
                () -> assertNotEquals(0, shares.size()));

    }

}