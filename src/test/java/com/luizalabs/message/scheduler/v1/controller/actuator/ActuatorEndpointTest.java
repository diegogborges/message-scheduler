package com.luizalabs.message.scheduler.v1.controller.actuator;

import com.luizalabs.message.scheduler.base.BaseControllerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class ActuatorEndpointTest extends BaseControllerTest {

  @LocalServerPort
  protected Integer port = 0;

  @Test
  void healthCheck() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/actuator/health", String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Assertions.assertNotNull(response.getBody());
    Assertions.assertEquals(response.getBody(), "{\"status\":\"UP\"}");
  }
}