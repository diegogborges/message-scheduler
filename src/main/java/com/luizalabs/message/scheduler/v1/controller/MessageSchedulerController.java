package com.luizalabs.message.scheduler.v1.controller;

import com.luizalabs.message.scheduler.model.MessageScheduler;
import com.luizalabs.message.scheduler.model.MessageTypeScheduler;
import com.luizalabs.message.scheduler.service.MessageSchedulerService;
import com.luizalabs.message.scheduler.v1.model.input.MessageSchedulerInput;

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

  private MessageSchedulerService messageSchedulerService;

  @Autowired
  public MessageSchedulerController(MessageSchedulerService messageSchedulerService) {
    this.messageSchedulerService = messageSchedulerService;
  }

  @PostMapping()
  public ResponseEntity<List<MessageTypeScheduler>> save(
      @RequestBody @Valid MessageSchedulerInput messageSchedulerInput) {

    return new ResponseEntity<>(
        messageSchedulerService.save(messageSchedulerInput), null, HttpStatus.CREATED);
  }

}
