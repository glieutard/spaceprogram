package com.spaceprogram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.config.name: spaceprogram"},
classes = {
		SpaceprogramApplication.class
})
public class SpaceprogramApplicationTests {

	@Test
	public void contextLoads() {
	}

}
