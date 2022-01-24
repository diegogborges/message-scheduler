package com.luizalabs.message.scheduler.v1.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerControllerImpl {
  public SwaggerControllerImpl() {
  }

  @RequestMapping({"/", "/docs", "/swagger", "/swagger-ui"})
  public String redirect() {
    return "redirect:/swagger-ui.html";
  }
}
