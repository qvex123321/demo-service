package com.demo.exam.demoservice.db.rmdb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.exam.demoservice.db.rmdb.entity.ForeignExchangeRate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ForeignExchangeRateMapper extends BaseMapper<ForeignExchangeRate> {

    void batchSaveOrUpdate(List<ForeignExchangeRate> entities);
}

