package com.luizalabs.message.scheduler.v1.controller;

import com.luizalabs.message.scheduler.service.MessageSchedulerService;
import com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest;
import com.luizalabs.message.scheduler.v1.model.response.MessageSchedulerResponse;

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
  public ResponseEntity<MessageSchedulerResponse> getByMessageScheduledId(
      @PathVariable Long messageScheduledId) {
    final MessageSchedulerResponse messageSchedulerResponse
        = this.messageSchedulerService.findById(messageScheduledId);
    return new ResponseEntity<>(messageSchedulerResponse, null, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<MessageSchedulerResponse> save(
      @RequestBody @Valid MessageSchedulerRequest messageSchedulerInput) {

    final MessageSchedulerResponse messageSchedulerResponse
        = this.messageSchedulerService.save(messageSchedulerInput);

    return new ResponseEntity<>(messageSchedulerResponse, null, HttpStatus.CREATED);
  }

  @DeleteMapping("/{messageScheduledId}")
  public ResponseEntity<Boolean> delete(@PathVariable Long messageScheduledId) {
    messageSchedulerService.delete(messageScheduledId);
    return new ResponseEntity<>(true, null, HttpStatus.OK);
  }
}
