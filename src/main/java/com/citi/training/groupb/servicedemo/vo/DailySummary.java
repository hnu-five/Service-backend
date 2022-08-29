package com.citi.training.groupb.servicedemo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailySummary {
    private String date;

    private Double buy;

    private Double sell;

    @JsonProperty("cumulative_net")
    private Double cumulativeNet;
}
