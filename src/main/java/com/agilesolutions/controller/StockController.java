package com.agilesolutions.controller;

import com.agilesolutions.config.ApplicationProperties;
import com.agilesolutions.dto.StockResponse;
import com.agilesolutions.model.DailyStockData;
import com.agilesolutions.model.StockData;
import com.agilesolutions.rest.StockClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/data")
public class StockController {

    @Qualifier("stockClient")
    private final StockClient stockClient;

    private final ApplicationProperties applicationProperties;

    private static final String MINUTE_INTERVAL = "1min";
    private static final String DAY_INTERVAL = "1day";


    @GetMapping("/stockPrices")
    StockResponse getLatestStockPrices(@PathVariable String company) {

        log.info("Get stock prices for: {}", company);
        StockData data = stockClient.getLatestStockPrices(company, MINUTE_INTERVAL, 1, applicationProperties.getKey());
        DailyStockData latestData = data.getValues().get(0);
        log.info("Get stock prices ({}) -> {}", company, latestData.getClose());
        return new StockResponse(Float.parseFloat(latestData.getClose()));
    }


}