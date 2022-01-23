package com.luizalabs.message.scheduler.exception;

public class MessageTypeNotFoundException extends RuntimeException {

  public MessageTypeNotFoundException(Integer id) {
    super(String.format("Message Type with id: %s not found!", id));
  }
}
