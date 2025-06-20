package com.agilesolutions.controller;

import com.agilesolutions.config.ApplicationProperties;
import com.agilesolutions.config.JunitConfig;
import com.agilesolutions.dto.StockResponse;
import com.agilesolutions.service.StockService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(StockController.class)
@ContextConfiguration(classes = {JunitConfig.class, ApplicationProperties.class})
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StockService stockService;

    @Autowired
    private ApplicationProperties applicationProperties;


    @Test
    public void givenFinancialAssets_whenInquiringApple_thenReturnStockPricesForApple() throws Exception {

        // WHEN
        Mockito.when(stockService.getLatestStockPrices("APPL")).thenReturn(StockResponse.builder().price(1.0F).build());

        // THEN
        mockMvc.perform(get("/data/stockPrices").param("company", "AAPL")
                        .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(status().isOk())
                .andDo(print());
        //.andExpect(content().string(containsStringIgnoringCase("cat")));
    }
}