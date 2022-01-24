package com.luizalabs.message.scheduler.domain.enumerable;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageStatusEnum {

  NOT_SENT(1, "NOT_SENT"),
  SENT(2, "SENT"),
  FAILED(3, "FAILED");

  private Integer value;
  private String description;

  @JsonCreator
  public static MessageStatusEnum find(final Integer value) {
    return Arrays.stream(MessageStatusEnum.values())
        .filter(s -> s.value.equals(value)).findFirst()
        .orElseThrow(() -> new IllegalArgumentException(String.valueOf(value)));
  }
}