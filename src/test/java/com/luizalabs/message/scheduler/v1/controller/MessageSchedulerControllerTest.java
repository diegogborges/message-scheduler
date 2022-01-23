package com.luizalabs.message.scheduler.v1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizalabs.message.scheduler.repository.MessageSchedulerRepository;
import com.luizalabs.message.scheduler.service.MessageSchedulerService;
import com.luizalabs.message.scheduler.v1.model.input.MessageSchedulerInput;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MessageSchedulerControllerTest {

  @Autowired
  private MessageSchedulerRepository messageSchedulerRepository;

  @Autowired
  private MessageSchedulerService messageSchedulerService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  private final String urlPathResource = MessageFormat.format(
      "{0}{1}", "/v1/", "message/scheduler");

  @BeforeEach
  void before() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  @Rollback
  @Transactional
  @SneakyThrows
  void testValidateSchemaSaveMessageScheduler() {
    this.mockMvc.perform(post(urlPathResource)
        .content(asJsonString(getMessageTypeScheduler()))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();
  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private MessageSchedulerInput getMessageTypeScheduler() {
    return MessageSchedulerInput.builder()
        .email("teste@gmail.com")
        .phone("5599999999999")
        .customerUuid("uuid")
        .sendDate(LocalDateTime.now().plusDays(2))
        .messageTypes(Arrays.asList(1)).build();
  }
}
