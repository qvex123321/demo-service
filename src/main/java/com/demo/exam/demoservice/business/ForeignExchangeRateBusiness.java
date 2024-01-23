package com.demo.exam.demoservice.business;

import com.demo.exam.demoservice.model.vo.ForeignExchangeRateVo;

import java.time.ZonedDateTime;
import java.util.List;

public interface ForeignExchangeRateBusiness {
    void executeTask();

    List<ForeignExchangeRateVo> getDailyForeignExchangeRates(String currency, ZonedDateTime startTime, ZonedDateTime endTime);
}
