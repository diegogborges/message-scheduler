package com.luizalabs.message.scheduler.model.enumerable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.luizalabs.message.scheduler.exception.MessageTypeNotFoundException;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

  SMS(1),
  MAIL(2),
  PUSH(3),
  WHATSAPP(4);

  private final Integer value;

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
}
