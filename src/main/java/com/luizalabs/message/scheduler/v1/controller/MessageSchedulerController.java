package com.luizalabs.message.scheduler.v1.controller;

import com.luizalabs.message.scheduler.service.MessageSchedulerService;
import com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest;
import com.luizalabs.message.scheduler.v1.model.response.MessageSchedulerResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/message/scheduler")
public class MessageSchedulerController {

  private final MessageSchedulerService messageSchedulerService;

  @Autowired
  public MessageSchedulerController(MessageSchedulerService messageSchedulerService) {
    this.messageSchedulerService = messageSchedulerService;
  }

  @GetMapping("/{messageScheduledId}")
  @Operation(summary = "Get Message Scheduler")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid id"),
          @ApiResponse(responseCode = "404", description = "Message Scheduler not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "502", description = "Bad Gateway")
      }
  )
  public ResponseEntity<MessageSchedulerResponse> getByMessageScheduledId(
      @PathVariable Long messageScheduledId) {
    final MessageSchedulerResponse messageSchedulerResponse
        = this.messageSchedulerService.findById(messageScheduledId);
    return new ResponseEntity<>(messageSchedulerResponse, null, HttpStatus.OK);
  }

  @PostMapping()
  @Operation(summary = "Create Message Scheduler")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Created"),
          @ApiResponse(responseCode = "400", description = "Invalid Request Body"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public ResponseEntity<MessageSchedulerResponse> save(
      @RequestBody @Valid MessageSchedulerRequest messageSchedulerInput) {

    final MessageSchedulerResponse messageSchedulerResponse
        = this.messageSchedulerService.save(messageSchedulerInput);

    return new ResponseEntity<>(messageSchedulerResponse, null, HttpStatus.CREATED);
  }

  @DeleteMapping("/{messageScheduledId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Delete Message Scheduler")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "No Content"),
          @ApiResponse(responseCode = "400", description = "Invalid id"),
          @ApiResponse(responseCode = "404", description = "Message Scheduler not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "502", description = "Bad Gateway")
      }
  )
  public void delete(@PathVariable Long messageScheduledId) {
    messageSchedulerService.delete(messageScheduledId);
  }
}
