package com.luizalabs.message.scheduler.v1.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.luizalabs.message.scheduler.domain.enumerable.MessageTypeEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MessageSchedulerRequest {

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime sendDate;
  private String email;
  private String phone;
  private String customerUuid;
  private String message;
  private List<Integer> messageTypes;

  public void getAllMessageTypes() {
    this.messageTypes = new ArrayList<>();
    MessageTypeEnum.getAllEnums().forEach(e -> this.getMessageTypes().add(e.getValue()));
  }
}
