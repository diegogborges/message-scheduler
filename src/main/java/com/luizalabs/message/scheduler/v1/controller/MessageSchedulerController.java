package com.luizalabs.message.scheduler.v1.controller;

import com.luizalabs.message.scheduler.domain.entity.MessageTypeScheduler;
import com.luizalabs.message.scheduler.service.MessageSchedulerService;
import com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @PostMapping()
  public ResponseEntity<List<MessageTypeScheduler>> save(
      @RequestBody @Valid MessageSchedulerRequest messageSchedulerInput) {

    return new ResponseEntity<>(
        messageSchedulerService.save(messageSchedulerInput), null, HttpStatus.CREATED);
  }

}
