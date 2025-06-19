package com.agilesolutions.model;

import lombok.Data;

import java.util.List;

@Data
public class StockData {

    private List<DailyStockData> values;

}