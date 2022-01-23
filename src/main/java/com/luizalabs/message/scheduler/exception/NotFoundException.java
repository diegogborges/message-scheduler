package com.luizalabs.message.scheduler.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String details) {
    super(details);
  }
}