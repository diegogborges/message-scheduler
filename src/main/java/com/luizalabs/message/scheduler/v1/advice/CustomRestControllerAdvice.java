package com.luizalabs.message.scheduler.v1.advice;


import com.luizalabs.message.scheduler.exception.InternalServerErrorException;
import com.luizalabs.message.scheduler.exception.NotFoundException;
import com.luizalabs.message.scheduler.v1.model.response.BaseResponseError;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class CustomRestControllerAdvice {
  @ExceptionHandler({
      HttpMessageNotReadableException.class,
      MethodArgumentTypeMismatchException.class,
      MethodArgumentNotValidException.class,
      ValidationException.class
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BaseResponseError catchBadRequestException(Throwable t) {
    return new BaseResponseError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), t.getMessage());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public BaseResponseError catchNotFoundException(Throwable t) {
    return new BaseResponseError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), t.getMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public BaseResponseError catchMethodNotAllowedException(Throwable t) {
    return new BaseResponseError(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), t.getMessage());
  }

  @ExceptionHandler({InternalServerErrorException.class, Throwable.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponseError catchInternalServerErrorException(Throwable t) {
    return new BaseResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), t.getMessage());
  }
}
