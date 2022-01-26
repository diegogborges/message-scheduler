package com.luizalabs.message.scheduler.v1.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.luizalabs.message.scheduler.domain.enumerable.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@Schema
public class MessageSchedulerRequest {

  @NotNull
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @Schema(required = true, description = "Date to send message", example = "2022-01-22T17:34:29 with format yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime sendDate;

  @Schema(required = false, description = "E-mail", example = "diego@gmail.com")
  private String email;

  @Schema(required = false, description = "Phone to send SMS and WhatsApp", example = "5599999999999")
  private String phone;

  @Schema(required = false, description = "Uuid to send Push", example = "Uuid format")
  private String customerUuid;

  @Schema(required = false, description = "Message to communication", example = "Hello everyone")
  private String message;

  @Schema(required = false, description = "Message Types, can be empty to save for all types or using their ids, 1 - SMS, 2 - email, 3 - push, 4 - WhatsApp", example = "[1,2,3,4]")
  private List<Integer> messageTypes;

  public void getAllMessageTypes() {
    this.messageTypes = new ArrayList<>();
    MessageTypeEnum.getAllEnums().forEach(e -> this.getMessageTypes().add(e.getValue()));
  }
}
