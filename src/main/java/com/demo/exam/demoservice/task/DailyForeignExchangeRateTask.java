package com.demo.exam.demoservice.task;

import com.demo.exam.demoservice.business.ForeignExchangeRateBusiness;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyForeignExchangeRateTask {
    private final ForeignExchangeRateBusiness foreignExchangeRateBusiness;

    @Scheduled(cron = "0 0 18 * * ?", zone="Asia/Taipei")
    public void saveForeignExchangeRateTask(){
        foreignExchangeRateBusiness.executeTask();
    }
}
