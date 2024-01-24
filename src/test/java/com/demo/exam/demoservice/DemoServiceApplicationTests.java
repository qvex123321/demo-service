package com.demo.exam.demoservice;

import com.demo.exam.demoservice.business.ForeignExchangeRateBusiness;
import com.demo.exam.demoservice.controller.ForeignExchangeRateController;
import com.demo.exam.demoservice.enums.ResponseCode;
import com.demo.exam.demoservice.model.bo.QueryForeignExchangeRateBo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoServiceApplicationTests {
	@Autowired
	private ForeignExchangeRateBusiness foreignExchangeRateBusiness;

	@Autowired
	private ForeignExchangeRateController foreignExchangeRateController;

	@Test
	void testTask() {
		foreignExchangeRateBusiness.executeTask();
	}
	@Test
	void testApi() {
		QueryForeignExchangeRateBo bo = new QueryForeignExchangeRateBo("2022/01/01", "2024/01/01", "USD");
		Assertions.assertEquals(ResponseCode.INPUT_DURATION_INVALID.getCode(), foreignExchangeRateController.getFeignExchangeRate(bo).getError().getCode(), "開始時間超過範圍測試失敗!");


		bo = new QueryForeignExchangeRateBo("2023/01/20", "2024/01/25", "USD");
		Assertions.assertEquals(ResponseCode.INPUT_DURATION_INVALID.getCode(), foreignExchangeRateController.getFeignExchangeRate(bo).getError().getCode(), "結束時間超過範圍測試失敗!");

		bo = new QueryForeignExchangeRateBo("2023/01/20", "2024/01/10", "USD");
		Assertions.assertEquals(ResponseCode.INPUT_DURATION_INVALID.getCode(), foreignExchangeRateController.getFeignExchangeRate(bo).getError().getCode(), "時間格式錯誤測試失敗!");
	}

}
