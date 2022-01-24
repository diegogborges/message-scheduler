package com.luizalabs.message.scheduler.exception;

public class BadGatewayException extends RuntimeException {
  public BadGatewayException(String details) {
    super(details);
  }
}