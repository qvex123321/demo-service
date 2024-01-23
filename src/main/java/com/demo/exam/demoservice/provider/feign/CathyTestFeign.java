package com.demo.exam.demoservice.provider.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cathy-test-provider", url = "https://openapi.taifex.com.tw")
public interface CathyTestFeign {
    @RequestMapping(value = "/v1/DailyForeignExchangeRates", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    byte[] getDailyForeignExchangeRates();

}
