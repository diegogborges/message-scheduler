package com.luizalabs.message.scheduler.exception;

public class ConflictException extends RuntimeException {
  public ConflictException(String details) {
    super(details);
  }
}