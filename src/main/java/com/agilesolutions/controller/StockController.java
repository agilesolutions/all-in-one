package com.agilesolutions.controller;

import com.agilesolutions.dto.StockResponse;
import com.agilesolutions.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/data")
@AllArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/stockPrices")
    StockResponse getLatestStockPrices(@PathVariable String company) {

        log.info("Get stock prices for: {}", company);
        return stockService.getLatestStockPrices(company);
    }


}