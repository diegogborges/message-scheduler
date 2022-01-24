package com.luizalabs.message.scheduler.v1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizalabs.message.scheduler.domain.enumerable.MessageTypeEnum;
import com.luizalabs.message.scheduler.repository.MessageSchedulerRepository;
import com.luizalabs.message.scheduler.service.MessageSchedulerService;
import com.luizalabs.message.scheduler.v1.controller.base.BaseEndpointTest;
import com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest;
import com.luizalabs.message.scheduler.v1.model.response.MessageSchedulerResponse;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MessageSchedulerControllerTest extends BaseEndpointTest {

  @Autowired
  private MessageSchedulerRepository messageSchedulerRepository;

  @Autowired
  private MessageSchedulerService messageSchedulerService;

  private final String urlPathResource = MessageFormat.format(
      "{0}{1}", "/v1/", "message/scheduler");

  @Test
  @Rollback
  @Transactional
  @SneakyThrows
  void saveMessageScheduler() {
    final MessageSchedulerRequest messageSchedulerRequest = getMessageTypeScheduler();
    final MessageSchedulerResponse response = super.postIsCreated(
        this.urlPathResource,
        getMessageTypeScheduler(),
        MessageSchedulerResponse.class
    );

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getCustomerUuid(), messageSchedulerRequest.getCustomerUuid());
    Assertions.assertEquals(response.getEmail(), messageSchedulerRequest.getEmail());
    Assertions.assertEquals(response.getPhone(), messageSchedulerRequest.getPhone());
  }

  @Test
  @Rollback
  @Transactional
  @SneakyThrows
  void saveMessageSchedulerWithNonExistentMessageType() {
    final MessageSchedulerRequest messageSchedulerRequest = getMessageTypeScheduler();
    messageSchedulerRequest.setMessageTypes(Arrays.asList(5, MessageTypeEnum.WHATSAPP.getValue()));
    final String json = this.mockMvc.perform(post(urlPathResource)
        .content(asJsonString(messageSchedulerRequest))
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().is5xxServerError())
        .andReturn()
        .getResponse()
        .getContentAsString();
  }

  @Test
  @Rollback
  @Transactional
  @SneakyThrows
  void saveMessageSchedulerWithEmptyListMessageType() {
    final MessageSchedulerRequest messageSchedulerRequest = getMessageTypeScheduler();
    messageSchedulerRequest.setMessageTypes(null);

    final MessageSchedulerResponse response = super.postIsCreated(
        this.urlPathResource,
        messageSchedulerRequest,
        MessageSchedulerResponse.class
    );

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getCustomerUuid(), messageSchedulerRequest.getCustomerUuid());
    Assertions.assertEquals(response.getEmail(), messageSchedulerRequest.getEmail());
    Assertions.assertEquals(response.getPhone(), messageSchedulerRequest.getPhone());
    Assertions.assertEquals(4, response.getMessageTypes().size());
  }

  @Test
  @SneakyThrows
  void saveMessageSchedulerWithOutContent() {
    super.postIsBadRequest(
        this.urlPathResource,
        null,
        "Required request body is missing: public org.springframework.http.ResponseEntity<com.luizalabs.message.scheduler.v1.model.response.MessageSchedulerResponse> com.luizalabs.message.scheduler.v1.controller.MessageSchedulerController.save(com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest)"
    );
  }

  @Test
  @SneakyThrows
  void saveMessageSchedulerWithWrongPath() {
    super.patchIsMethodNotAllowed(
        this.urlPathResource+"/wrong",
        null,
        "Request method 'PATCH' not supported"
    );
  }

  @Test
  @SneakyThrows
  void deleteMessageScheduler() {
    super.postIsCreated(
        this.urlPathResource,
        getMessageTypeScheduler(),
        MessageSchedulerResponse.class
    );
    final String json = this.mockMvc.perform(delete(urlPathResource + "/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
  }

  @Test
  @SneakyThrows
  void deleteMessageSchedulerWithNonExistentId() {
    final String json = this.mockMvc.perform(delete(urlPathResource + "/999")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isNotFound())
        .andReturn()
        .getResponse()
        .getContentAsString();
  }

  @Test
  void getMessageSchedulerOneById() throws Throwable {
    MessageSchedulerResponse response =
        super.getIsOk(this.urlPathResource + "/" + 1, MessageSchedulerResponse.class);

    Assertions.assertNotNull(response);
  }

  @Test
  @SneakyThrows
  void getMessageSchedulerOneByIdIsBadRequest() {
    super.getIsBadRequest(this.urlPathResource + "/abc", "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; nested exception is java.lang.NumberFormatException: For input string: \"abc\"");
  }

  @Test
  @SneakyThrows
  void getMessageSchedulerOneByIdIsNotFound() {
    super.getIsNotFound(this.urlPathResource + "/" + 999, "Message Scheduler with id: 999 not found!");
  }

  @Test
  @SneakyThrows
  void deleteMessageSchedulerIsNotFound() {
    super.deleteIsNotFound(this.urlPathResource + "/" + 9999, "Message Scheduler with id: 9999 not found!");
  }

  @Test
  @SneakyThrows
  void deleteMessageSchedulerIsBadRequest() {
    super.deleteIsBadRequest(this.urlPathResource + "/" + "abc",
        "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; nested exception is java.lang.NumberFormatException: For input string: \"abc\"");
  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private MessageSchedulerRequest getMessageTypeScheduler() {
    return MessageSchedulerRequest.builder()
        .email("teste@gmail.com")
        .phone("5599999999999")
        .customerUuid("uuid")
        .sendDate(LocalDateTime.now().plusDays(2))
        .message("the message")
        .messageTypes(Arrays.asList(
            MessageTypeEnum.MAIL.getValue(),
            MessageTypeEnum.PUSH.getValue()
        )).build();
  }
}
