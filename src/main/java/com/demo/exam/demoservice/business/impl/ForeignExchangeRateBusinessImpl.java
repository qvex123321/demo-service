package com.demo.exam.demoservice.business.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.exam.demoservice.business.ForeignExchangeRateBusiness;
import com.demo.exam.demoservice.db.rmdb.entity.ForeignExchangeRate;
import com.demo.exam.demoservice.model.vo.ForeignExchangeRateVo;
import com.demo.exam.demoservice.provider.feign.CathyTestFeign;
import com.demo.exam.demoservice.provider.vo.DailyForeignExchangeRateVo;
import com.demo.exam.demoservice.service.ForeignExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForeignExchangeRateBusinessImpl implements ForeignExchangeRateBusiness {
    private final CathyTestFeign cathyTestFeign;
    private final ForeignExchangeRateService foreignExchangeRateService;

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public void executeTask() {
        byte[] data = cathyTestFeign.getDailyForeignExchangeRates();
        List<DailyForeignExchangeRateVo> vos = deserializeDataToObjectList(data);
        List<ForeignExchangeRate> entities = vos.stream().filter(this::validateData).map(this::toEntity).collect(Collectors.toList());
        foreignExchangeRateService.batchSaveOrUpdate(entities);
        log.info("Finished saveForeignExchangeRateTask task.");
    }

    private List<DailyForeignExchangeRateVo> deserializeDataToObjectList(byte[] data) {
        String dataString = new String(data, StandardCharsets.UTF_8);
        JSONArray jsonArray = JSONArray.parseArray(dataString);
        List<DailyForeignExchangeRateVo> res = new ArrayList<>();
        for(int i = 0; i<jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            res.add(DailyForeignExchangeRateVo
                    .builder()
                    .date(jsonObject.getString("Date"))
                    .usdToNtd(jsonObject.getString("USD/NTD"))
                    .build());
        }
        return res;
    }

    private boolean validateData(DailyForeignExchangeRateVo vo) {
        return StringUtils.isNotBlank(vo.getUsdToNtd()) && StringUtils.isNotBlank(vo.getUsdToNtd());
    }

    private ForeignExchangeRate toEntity(DailyForeignExchangeRateVo vo){
        LocalDate localDate = LocalDate.parse(vo.getDate(), dtf);
        ZonedDateTime shownDate = localDate.atStartOfDay(ZoneId.systemDefault());
        return ForeignExchangeRate.builder()
                .shownDate(shownDate)
                .usdToNtd(Double.valueOf(vo.getUsdToNtd()))
                .build();
    }

    @Override
    public List<ForeignExchangeRateVo> getDailyForeignExchangeRates(String currency, ZonedDateTime startTime, ZonedDateTime endTime) {
        List<ForeignExchangeRate> entities = foreignExchangeRateService.getForeignExchangeRateByDuration(startTime, endTime);
        return entities.stream().map(e -> toVo(currency, e)).collect(Collectors.toList());
    }

    private ForeignExchangeRateVo toVo(String currency, ForeignExchangeRate entity) {
        ForeignExchangeRateVo res = ForeignExchangeRateVo.builder()
                .date(entity.getShownDate().withZoneSameInstant(ZoneId.systemDefault()).toLocalDate().format(dtf))
                .build();
        switch (currency) {
            case "usd": {
                res.setUsd(String.valueOf(entity.getUsdToNtd()));
            }
            default: {
            }
        }
        return res;
    }
}
