package com.luizalabs.message.scheduler.v1.controller;

import com.luizalabs.message.scheduler.base.BaseControllerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class SwaggerControllerTest extends BaseControllerTest {

  @LocalServerPort
  private final Integer port = 0;

  @Test
  void redirectWithSuccess() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/swagger", String.class);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertNotNull(response.getBody());
    Assertions.assertTrue(response.getBody().contains("Swagger UI"));
  }
}