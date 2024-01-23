package com.demo.exam.demoservice.controller;

import com.demo.exam.demoservice.business.ForeignExchangeRateBusiness;
import com.demo.exam.demoservice.enums.ResponseCode;
import com.demo.exam.demoservice.model.bo.QueryForeignExchangeRateBo;
import com.demo.exam.demoservice.model.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class foreignExchangeRateController {
    private final ForeignExchangeRateBusiness foreignExchangeRateBusiness;

    @PostMapping("/api/v1/feignExchangeRates")
    public ResponseVo getFeignExchangeRate(@RequestBody QueryForeignExchangeRateBo bo) {
        if(StringUtils.isBlank(bo.getStartDate()) || StringUtils.isBlank(bo.getEndDate())) {
            return new ResponseVo(ResponseCode.INPUT_DURATION_INVALID);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate startDate = LocalDate.parse(bo.getStartDate(), dtf);
        LocalDate endDate = LocalDate.parse(bo.getEndDate(), dtf);
        ZonedDateTime startTime = startDate.atStartOfDay(ZoneId.systemDefault()).minusSeconds(1);
        ZonedDateTime endTime = endDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endTimeLimit = ZonedDateTime.now().minusDays(1).minusSeconds(1);
        ZonedDateTime startTimeLimit = endTimeLimit.minusYears(1);
        if(startTime.isBefore(startTimeLimit) || endTime.isAfter(endTimeLimit) || endTime.isBefore(startTime)) {
            return new ResponseVo(ResponseCode.INPUT_DURATION_INVALID);
        }
        if(StringUtils.isBlank(bo.getCurrency()) || !"usd".equalsIgnoreCase(bo.getCurrency())) {
            return new ResponseVo(new ArrayList<>());
        }
        return new ResponseVo(foreignExchangeRateBusiness.getDailyForeignExchangeRates(bo.getCurrency(), startTime, endTime));
    }
}
