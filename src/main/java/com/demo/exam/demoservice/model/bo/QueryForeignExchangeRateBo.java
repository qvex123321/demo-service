package com.demo.exam.demoservice.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryForeignExchangeRateBo {
    private String startDate;
    private String EndDate;
    private String currency;
}
