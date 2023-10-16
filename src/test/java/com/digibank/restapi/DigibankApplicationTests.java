package com.digibank.restapi;

import com.digibank.restapi.model.Example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DigibankApplicationTests {

	@Test
	void check_lombok_usage_with_sample_data() {
		var ex = new Example();
		ex.setData("sample");
		Assertions.assertEquals("sample",ex.getData());
	}

}
