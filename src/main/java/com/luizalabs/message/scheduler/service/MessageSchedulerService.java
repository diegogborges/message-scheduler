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
import com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest;
import com.luizalabs.message.scheduler.v1.model.response.MessageSchedulerResponse;
import com.luizalabs.message.scheduler.v1.model.response.MessageTypeDto;

import java.util.ArrayList;
import java.util.List;

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

  public MessageSchedulerResponse save(final MessageSchedulerRequest messageSchedulerRequest) {
    final MessageScheduler messageScheduler =
        this.messageSchedulerAssembler.toMessageSchedulerModel(messageSchedulerRequest);

    List<MessageTypeScheduler> messageTypeSchedulerList = new ArrayList<>();
    messageSchedulerRequest.getMessageTypes().forEach(t -> {
      final MessageTypeEnum messageTypeEnum = MessageTypeEnum.find(t);
      final MessageTypeScheduler messageTypeScheduler = MessageTypeScheduler.builder()
          .messageScheduler(messageScheduler)
          .messageType(MessageType.builder()
              .id(t).description(messageTypeEnum.getDescription())
              .build())
          .messageStatus(MessageStatus.builder().id(MessageStatusEnum.NOT_SENT.getValue()).build())
          .build();
      messageTypeSchedulerList.add(messageTypeScheduler);
    });

    messageScheduler.setMessageTypeSchedulers(messageTypeSchedulerList);
    final MessageScheduler messageSchedulerResult =
        messageSchedulerRepository.save(messageScheduler);

    return prepareResponse(messageSchedulerResult);
  }

  public void delete(final Long messageScheduledId) {
    final MessageScheduler messageScheduler = this.findByIdOrThrow(messageScheduledId);
    messageSchedulerRepository.delete(messageScheduler);
  }

  public MessageSchedulerResponse findById(final Long messageScheduledId) {
    final MessageScheduler messageScheduler = this.findByIdOrThrow(messageScheduledId);
    return prepareResponse(messageScheduler);
  }

  private MessageScheduler findByIdOrThrow(final Long messageScheduledId) {
    return messageSchedulerRepository.findById(messageScheduledId)
        .orElseThrow(() -> new NotFoundException(
            String.format("Message Scheduler with id: %s not found!", messageScheduledId)));
  }

  private MessageSchedulerResponse prepareResponse(final MessageScheduler messageSchedulerResult) {
    List<MessageTypeDto> messageTypeDtoList = new ArrayList<>();

    messageSchedulerResult.getMessageTypeSchedulers().forEach(m -> {
      final MessageStatusEnum messageStatusEnum =
          MessageStatusEnum.find(m.getMessageStatus().getId());
      final MessageType messageType = m.getMessageType();
      messageTypeDtoList.add(MessageTypeDto.builder()
          .description(messageType.getDescription())
          .statusSend(messageStatusEnum.getDescription())
          .build());
    });

    return MessageSchedulerResponse.builder()
        .sendDate(messageSchedulerResult.getSendDate())
        .email(messageSchedulerResult.getEmail())
        .customerUuid(messageSchedulerResult.getCustomerUuid())
        .phone(messageSchedulerResult.getPhone())
        .message(messageSchedulerResult.getMessage())
        .messageTypes(messageTypeDtoList)
        .build();
  }
}
