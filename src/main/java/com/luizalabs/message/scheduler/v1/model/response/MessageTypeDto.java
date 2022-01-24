package com.luizalabs.message.scheduler.v1.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MessageTypeDto {

  private String description;
  private String statusSend;
}
