package com.example.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void getFromCoupinConttollerTest(){
		String url = "http://localhost:8080/student/2";
		var expected = HttpGetTestManger.getGetResponse(url,StudentDTO.class);
		var current = new StudentDTO("sharon", "tasa", 2, 1, 93.4);
		Assert.assertEquals(current,expected);
	}

	@Test
	void statusTest(){
		String url = "http://localhost:8080/student";
		var expected = HttpGetTestManger.getHttpCode(url);
		Assert.assertEquals(2,expected/200);
	}

}
