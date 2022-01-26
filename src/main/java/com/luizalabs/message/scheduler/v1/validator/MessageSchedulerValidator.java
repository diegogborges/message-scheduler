package com.luizalabs.message.scheduler.v1.validator;

import com.luizalabs.message.scheduler.domain.enumerable.MessageTypeEnum;
import com.luizalabs.message.scheduler.exception.MissingParameterException;
import com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;

@Component
public class MessageSchedulerValidator {

  public void validatesFieldsByMessageType(final MessageSchedulerRequest messageSchedulerRequest,
                                           final Errors errors) {

    final List<Integer> messageTypesId = Optional.ofNullable(messageSchedulerRequest)
        .map(MessageSchedulerRequest::getMessageTypes)
        .orElse(Collections.emptyList());

    if (messageTypesId.isEmpty()) {
      ValidationUtils.rejectIfEmptyOrWhitespace(
          errors, "phone", "error.phone", "phone is required");
      ValidationUtils.rejectIfEmptyOrWhitespace(
          errors, "email", "error.email", "email is required");
      ValidationUtils.rejectIfEmptyOrWhitespace(
          errors, "customerUuid", "error.customerUuid", "customerUuid is required");
    }

    if (messageTypesId.contains(MessageTypeEnum.SMS.getValue())
        || messageTypesId.contains(MessageTypeEnum.WHATSAPP.getValue())) {
      ValidationUtils.rejectIfEmptyOrWhitespace(
          errors, "phone", "error.phone", "phone is required");
    }

    if (messageTypesId.contains(MessageTypeEnum.PUSH.getValue())) {
      ValidationUtils.rejectIfEmptyOrWhitespace(
          errors, "customerUuid", "error.customerUuid", "customerUuid is required");
    }

    if (messageTypesId.contains(MessageTypeEnum.MAIL.getValue())) {
      ValidationUtils.rejectIfEmptyOrWhitespace(
          errors, "email", "error.email", "email is required");
    }

    if (errors.hasErrors()) {
      throw new MissingParameterException(messageError(errors.getAllErrors()));
    }
  }

  private String messageError(final List<ObjectError> errors) {
    final List<String> listErrors = new ArrayList<>();
    errors.forEach(e ->
        listErrors.add(e.getDefaultMessage()));
    return listErrors.toString();
  }
}
