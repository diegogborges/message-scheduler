package com.luizalabs.message.scheduler.v1.controller;

import com.luizalabs.message.scheduler.domain.entity.MessageScheduler;
import com.luizalabs.message.scheduler.domain.entity.MessageTypeScheduler;
import com.luizalabs.message.scheduler.service.MessageSchedulerService;
import com.luizalabs.message.scheduler.service.MessageTypeSchedulerService;
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
  private final MessageTypeSchedulerService messageTypeSchedulerService;

  @Autowired
  public MessageSchedulerController(MessageSchedulerService messageSchedulerService,
                                    MessageTypeSchedulerService messageTypeSchedulerService) {
    this.messageSchedulerService = messageSchedulerService;
    this.messageTypeSchedulerService = messageTypeSchedulerService;
  }

  @PostMapping()
  public ResponseEntity<List<MessageTypeScheduler>> save(
      @RequestBody @Valid MessageSchedulerRequest messageSchedulerInput) {

    final MessageScheduler messageSchedulerResult
        = this.messageSchedulerService.save(messageSchedulerInput);
    final List<MessageTypeScheduler> messageTypeSchedulerResult
        = messageTypeSchedulerService.save(messageSchedulerResult, messageSchedulerInput);

    return new ResponseEntity<>(messageTypeSchedulerResult, null, HttpStatus.CREATED);
  }
}
