package com.luizalabs.message.scheduler.v1.controller.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizalabs.message.scheduler.base.BaseControllerTest;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class BaseEndpointTest extends BaseControllerTest {
  @Autowired
  private ObjectMapper mapper;

  protected <T> T getIsOk(String path, Class<T> responseType) throws Throwable {
    String response = super.mockMvc.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    return this.mapper.readValue(response, responseType);
  }

  protected void getIsNotFound(String path, String details) throws Throwable {
    super.mockMvc.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.NOT_FOUND.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.NOT_FOUND.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void getIsBadRequest(String path, String details) throws Throwable {
    super.mockMvc.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.BAD_REQUEST.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.BAD_REQUEST.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected <T> T postIsCreated(String path, Object request, Class<T> responseType) throws Throwable {
    String response = super.mockMvc.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andReturn().getResponse().getContentAsString();

    return this.mapper.readValue(response, responseType);
  }

  protected void postIsBadRequest(String path, Object request, String details) throws Throwable {
    super.mockMvc.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.BAD_REQUEST.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.BAD_REQUEST.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void patchIsMethodNotAllowed(String path, Object request, String details) throws Throwable {
    super.mockMvc.perform(MockMvcRequestBuilders.patch(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.METHOD_NOT_ALLOWED.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void deleteIsBadRequest(String path, String details) throws Throwable {
    super.mockMvc.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.BAD_REQUEST.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.BAD_REQUEST.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void deleteIsNotFound(String path, String details) throws Throwable {
    super.mockMvc.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.NOT_FOUND.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.NOT_FOUND.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }
}
