package com.luizalabs.message.scheduler.domain.enumerable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.luizalabs.message.scheduler.exception.MessageTypeNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

  SMS(1, "sms"),
  MAIL(2, "email"),
  PUSH(3, "push"),
  WHATSAPP(4, "whatsApp");

  private final Integer value;
  private final String description;

  public static MessageTypeEnum getValue(final Integer value) {
    for (MessageTypeEnum e : MessageTypeEnum.values()) {
      if (e != null && e.value.equals(value)) {
        return e;
      }
    }
    return null;
  }

  @JsonCreator
  public static MessageTypeEnum find(final Integer value) {
    return Arrays.asList(MessageTypeEnum.values()).stream()
        .filter(s -> s.value.equals(value)).findFirst()
        .orElseThrow(() -> new MessageTypeNotFoundException(value));
  }

  public static List<MessageTypeEnum> getAllEnums() {
    return new ArrayList<>(Arrays.asList(MessageTypeEnum.values()));
  }
}
