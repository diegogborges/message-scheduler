package com.luizalabs.message.scheduler.service;

import com.luizalabs.message.scheduler.assembler.MessageSchedulerAssembler;
import com.luizalabs.message.scheduler.domain.entity.MessageScheduler;
import com.luizalabs.message.scheduler.domain.entity.MessageStatus;
import com.luizalabs.message.scheduler.domain.entity.MessageType;
import com.luizalabs.message.scheduler.domain.entity.MessageTypeScheduler;
import com.luizalabs.message.scheduler.domain.enumerable.MessageStatusEnum;
import com.luizalabs.message.scheduler.domain.enumerable.MessageTypeEnum;
import com.luizalabs.message.scheduler.exception.NotFoundException;
import com.luizalabs.message.scheduler.repository.MessageSchedulerRepository;
import com.luizalabs.message.scheduler.repository.MessageTypeSchedulerRepository;
import com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageTypeSchedulerService {

  private final MessageTypeSchedulerRepository messageTypeSchedulerRepository;

  @Autowired
  public MessageTypeSchedulerService(MessageTypeSchedulerRepository messageTypeSchedulerRepository) {
    this.messageTypeSchedulerRepository = messageTypeSchedulerRepository;
  }

  public List<MessageTypeScheduler> save(final MessageScheduler messageScheduler,
                                         final MessageSchedulerRequest messageSchedulerInput) {
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

    List<MessageTypeScheduler> messageTypeSchedulers = messageTypeSchedulerRepository.saveAll(messageTypeSchedulerList);

//    List<MessageType> collect = new ArrayList<>();
//    messageSchedulerInput.getMessageTypes().forEach(t -> {
//      final MessageTypeEnum messageTypeEnum = MessageTypeEnum.find(t);
//      collect.add(t);
//    });
//    List<MessageType> collect = messageTypeSchedulers.stream()
//        .map(MessageTypeScheduler::getMessageType)
//        .collect(Collectors.toList());

//    MessageSchedulerResponse messageSchedulerResponse =
//        MessageSchedulerResponse.builder()
//            .customerUuid(messageScheduler.getCustomerUuid())
//            .email(messageScheduler.getEmail())
//            .phone(messageScheduler.getPhone())
//            .sendDate(messageScheduler.getSendDate())
//            .messageTypes(Arrays.asList())
//            .build();

    return messageTypeSchedulers;
  }

  /*public void delete(final Long messageScheduledId) {
    messageTypeSchedulerRepository.delete(message);
  }*/
}
