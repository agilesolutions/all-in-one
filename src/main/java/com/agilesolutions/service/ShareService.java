package com.agilesolutions.service;

import com.agilesolutions.model.Share;
import com.agilesolutions.repository.ShareRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ShareService {

    @Autowired
    private final ShareRepository shareRepository;

    public Iterable<Share> getAllShares() {

        log.info("Get all Shares");

        return shareRepository.findAll();

    }

}
