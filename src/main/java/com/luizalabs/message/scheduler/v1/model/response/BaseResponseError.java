package com.luizalabs.message.scheduler.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseError {
  private Integer code;
  private String message;
  private String details;
}
