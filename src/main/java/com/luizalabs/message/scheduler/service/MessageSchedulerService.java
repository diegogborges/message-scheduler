package com.luizalabs.message.scheduler.service;

import com.luizalabs.message.scheduler.model.MessageScheduler;
import com.luizalabs.message.scheduler.model.MessageStatus;
import com.luizalabs.message.scheduler.model.MessageType;
import com.luizalabs.message.scheduler.model.MessageTypeScheduler;
import com.luizalabs.message.scheduler.model.enumerable.MessageStatusEnum;
import com.luizalabs.message.scheduler.model.enumerable.MessageTypeEnum;
import com.luizalabs.message.scheduler.repository.MessageSchedulerRepository;
import com.luizalabs.message.scheduler.repository.MessageTypeSchedulerRepository;
import com.luizalabs.message.scheduler.v1.model.input.MessageSchedulerInput;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSchedulerService {

  private final MessageTypeSchedulerRepository messageTypeSchedulerRepository;
  private final MessageSchedulerRepository messageSchedulerRepository;

  @Autowired
  public MessageSchedulerService(MessageTypeSchedulerRepository messageTypeSchedulerRepository,
                                 MessageSchedulerRepository messageSchedulerRepository) {
    this.messageTypeSchedulerRepository = messageTypeSchedulerRepository;
    this.messageSchedulerRepository = messageSchedulerRepository;
  }

  public List<MessageTypeScheduler> save(final MessageSchedulerInput messageSchedulerInput) {
    final MessageScheduler messageScheduler = saveMessageScheduler(messageSchedulerInput);

    List<MessageTypeScheduler> messageTypeSchedulerList = new ArrayList<>();
    messageSchedulerInput.getMessageTypes().forEach(t -> {
      MessageTypeEnum.find(t);
      final MessageTypeScheduler messageTypeScheduler = MessageTypeScheduler.builder()
          .messageScheduler(messageScheduler)
          .messageType(MessageType.builder().id(t).build())
          .messageStatus(MessageStatus.builder().id(MessageStatusEnum.NOT_SENT.getValue()).build())
          .build();
      messageTypeSchedulerList.add(messageTypeScheduler);
    });

    return messageTypeSchedulerRepository.saveAll(messageTypeSchedulerList);
  }

  private MessageScheduler saveMessageScheduler(MessageSchedulerInput messageSchedulerInput) {
    MessageScheduler messageScheduler = MessageScheduler.builder()
        .sendDate(messageSchedulerInput.getSendDate())
        .customerUuid(messageSchedulerInput.getCustomerUuid())
        .phone(messageSchedulerInput.getPhone())
        .email(messageSchedulerInput.getEmail())
        .build();

    messageScheduler = messageSchedulerRepository.save(messageScheduler);
    return messageScheduler;
  }
}
