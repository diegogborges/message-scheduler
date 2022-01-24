package com.luizalabs.message.scheduler.v1.model.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MessageSchedulerResponse {

  private LocalDateTime sendDate;
  private String email;
  private String phone;
  private String customerUuid;
  private List<MessageTypeDto> messageTypes;
}
