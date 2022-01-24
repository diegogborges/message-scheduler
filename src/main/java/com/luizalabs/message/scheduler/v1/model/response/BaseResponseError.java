package com.luizalabs.message.scheduler.v1.model.response;

public class BaseResponseError {
  private Integer code;
  private String message;
  private String details;

  public BaseResponseError() {
  }

  public BaseResponseError(Integer code, String message, String details) {
    this.code = code;
    this.message = message;
    this.details = details;
  }

  public Integer getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }

  public String getDetails() {
    return this.details;
  }
}
