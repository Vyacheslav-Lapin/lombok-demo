package ru.vlapin.demo.lombokdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyExceptionHandlerExampleController {

  @RequestMapping("makeNPE")
  public ModelAndView generateNpe() {
    String oops = null;
    oops.indexOf("oops!");
    return new ModelAndView("hello");
  }

  @ExceptionHandler(NullPointerException.class)
  public ModelAndView handleNpe(NullPointerException e) {
    return new ModelAndView("oops")
        .addObject(
            "message",
            "NullPointerException at %s"
                .formatted(System.currentTimeMillis()));
  }
}
