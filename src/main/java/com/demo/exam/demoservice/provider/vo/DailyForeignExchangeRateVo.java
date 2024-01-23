package com.demo.exam.demoservice.provider.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyForeignExchangeRateVo {
    private String date;
    private String usdToNtd;

}
