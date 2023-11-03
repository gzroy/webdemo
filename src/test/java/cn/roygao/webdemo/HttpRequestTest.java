package cn.roygao.webdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

    @Test
	void calculationShouldReturnCorrectMessage() throws Exception {
		assertThat(
            this.restTemplate.getForObject("http://localhost:" + port + "/calculate?input=10", String.class)).contains("200");
	}
}
