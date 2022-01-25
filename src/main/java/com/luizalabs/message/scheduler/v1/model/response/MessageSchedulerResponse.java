package com.luizalabs.message.scheduler.v1.model.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageSchedulerResponse {

  private LocalDateTime sendDate;
  private String email;
  private String phone;
  private String customerUuid;
  private String message;
  private List<MessageTypeDto> messageTypes;
}
