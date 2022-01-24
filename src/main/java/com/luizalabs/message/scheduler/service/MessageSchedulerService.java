package com.luizalabs.message.scheduler.service;

import com.luizalabs.message.scheduler.assembler.MessageSchedulerAssembler;
import com.luizalabs.message.scheduler.domain.entity.MessageScheduler;
import com.luizalabs.message.scheduler.exception.NotFoundException;
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
    final MessageScheduler messageScheduler =
        this.messageSchedulerAssembler.toMessageSchedulerModel(messageSchedulerRequest);
    return messageSchedulerRepository.save(messageScheduler);
  }

  public void delete(final MessageScheduler messageScheduled) {
    messageSchedulerRepository.delete(messageScheduled);
  }

  public MessageScheduler findByIdOrThrow(final Long messageScheduledId) {
    return messageSchedulerRepository.findById(messageScheduledId)
        .orElseThrow(() -> new NotFoundException(
            String.format("Message Scheduler with id: %s not found!", messageScheduledId)));
  }
}
