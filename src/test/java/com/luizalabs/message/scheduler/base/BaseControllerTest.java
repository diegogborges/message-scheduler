package com.luizalabs.message.scheduler.base;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {
  protected MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;

  @BeforeEach
  private void before() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }
}
