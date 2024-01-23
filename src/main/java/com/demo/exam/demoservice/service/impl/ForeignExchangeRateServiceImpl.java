package com.demo.exam.demoservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.exam.demoservice.db.rmdb.entity.ForeignExchangeRate;
import com.demo.exam.demoservice.db.rmdb.mapper.ForeignExchangeRateMapper;
import com.demo.exam.demoservice.service.ForeignExchangeRateService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForeignExchangeRateServiceImpl extends ServiceImpl<ForeignExchangeRateMapper, ForeignExchangeRate> implements ForeignExchangeRateService {
    private final ForeignExchangeRateMapper foreignExchangeRateMapper;
    final int BATCH_SIZE = 10;

    @Transactional
    @Override
    public void batchSaveOrUpdate(List<ForeignExchangeRate> entities) {
        int size = entities.size();
        if(size > BATCH_SIZE) {
            List<List<ForeignExchangeRate>> subLists = Lists.partition(entities, BATCH_SIZE);
            for(List<ForeignExchangeRate> subList : subLists) {
                foreignExchangeRateMapper.batchSaveOrUpdate(subList);
            }
        } else {
            foreignExchangeRateMapper.batchSaveOrUpdate(entities);
        }
    }

    @Override
    public List<ForeignExchangeRate> getForeignExchangeRateByDuration(ZonedDateTime startTime, ZonedDateTime endTime) {
        LambdaQueryWrapper<ForeignExchangeRate> wrapper = new LambdaQueryWrapper<>();
        wrapper.between(ForeignExchangeRate::getShownDate, startTime, endTime);
        return this.list(wrapper);
    }
}
