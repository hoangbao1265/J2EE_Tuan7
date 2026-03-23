package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void checkPassword() throws Exception {
		org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
		java.nio.file.Files.write(java.nio.file.Paths.get("hash.txt"), encoder.encode("admin123").getBytes());
	}

}
