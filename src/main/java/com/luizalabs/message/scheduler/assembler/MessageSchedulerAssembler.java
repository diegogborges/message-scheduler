package com.luizalabs.message.scheduler.assembler;

import com.luizalabs.message.scheduler.domain.entity.MessageScheduler;
import com.luizalabs.message.scheduler.v1.model.request.MessageSchedulerRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSchedulerAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public MessageScheduler toMessageSchedulerModel(final MessageSchedulerRequest messageSchedulerRequest) {
    modelMapper.typeMap(MessageSchedulerRequest.class, MessageScheduler.class);
    return modelMapper.map(messageSchedulerRequest, MessageScheduler.class);
  }

//  public MessageSchedulerRequest toTemplateDto(final MessageScheduler template) {
//    modelMapper.typeMap(MessageScheduler.class, TemplateDto.class)
//        .addMapping(Template::getSender, TemplateDto::setFromId)
//        .addMapping(Template::getSenderName, TemplateDto::setFromName);
//
//    return modelMapper.map(template, TemplateDto.class);
//  }
}
