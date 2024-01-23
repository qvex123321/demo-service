package com.demo.exam.demoservice.service;

import com.demo.exam.demoservice.db.rmdb.entity.ForeignExchangeRate;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

public interface ForeignExchangeRateService {
    @Transactional
    void batchSaveOrUpdate(List<ForeignExchangeRate> entities);

    List<ForeignExchangeRate> getForeignExchangeRateByDuration(ZonedDateTime startTime, ZonedDateTime endTime);
}
