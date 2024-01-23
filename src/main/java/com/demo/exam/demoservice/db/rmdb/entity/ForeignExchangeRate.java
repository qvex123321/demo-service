package com.demo.exam.demoservice.db.rmdb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
@TableName("foreign_exchange_rates")
public class ForeignExchangeRate {
    @TableId(type = IdType.AUTO)
    private Long id;
    private ZonedDateTime shownDate;
    private Double usdToNtd;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
