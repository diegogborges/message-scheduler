package com.luizalabs.message.scheduler.service;

import com.luizalabs.message.scheduler.assembler.MessageSchedulerAssembler;
import com.luizalabs.message.scheduler.domain.entity.MessageScheduler;
import com.luizalabs.message.scheduler.repository.MessageSchedulerRepository;
import com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSchedulerService {

  private final MessageSchedulerRepository messageSchedulerRepository;
  private final MessageSchedulerAssembler messageSchedulerAssembler;

  @Autowired
  public MessageSchedulerService(MessageSchedulerRepository messageSchedulerRepository,
                                 MessageSchedulerAssembler messageSchedulerAssembler) {
    this.messageSchedulerRepository = messageSchedulerRepository;
    this.messageSchedulerAssembler = messageSchedulerAssembler;
  }

  public MessageScheduler save(final MessageSchedulerRequest messageSchedulerRequest) {
    MessageScheduler messageScheduler =
        this.messageSchedulerAssembler.toMessageSchedulerModel(messageSchedulerRequest);
    return messageSchedulerRepository.save(messageScheduler);
  }
}
