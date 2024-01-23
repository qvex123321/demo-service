package com.demo.exam.demoservice;

import com.demo.exam.demoservice.business.ForeignExchangeRateBusiness;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoServiceApplicationTests {
	@Autowired
	private ForeignExchangeRateBusiness foreignExchangeRateBusiness;

	@Test
	void contextLoads() {
		foreignExchangeRateBusiness.executeTask();
	}

}
