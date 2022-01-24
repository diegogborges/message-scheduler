package com.luizalabs.message.scheduler.exception;

public class InternalServerErrorException extends RuntimeException {
  public InternalServerErrorException(String details) {
    super(details);
  }
}