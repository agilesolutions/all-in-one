package com.agilesolutions.controller;

import com.agilesolutions.model.Share;
import com.agilesolutions.service.ShareService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/shares")
public class ShareController {

    private final ShareService shareService;

    @GetMapping
    Iterable<Share> getAllShares() {

        log.info("Get all shares");
        return shareService.getAllShares();
    }


}