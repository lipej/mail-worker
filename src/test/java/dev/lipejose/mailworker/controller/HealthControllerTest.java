package dev.lipejose.mailworker.controller;

import dev.lipejose.mailworker.model.entity.HealthResponse;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HealthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getHealth() {
        HealthResponse body = this.restTemplate.getForObject("/health", HealthResponse.class);
        assertThat(body.getMessage()).isEqualTo("I am ok.");
        assertThat(body.getSuccess()).isEqualTo(true);
    }

}



