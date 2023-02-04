package ru.vlapin.demo.lombokdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyExceptionHandlerExampleController {

  @RequestMapping("makeNPE")
  @SuppressWarnings({"java:S2259", "java:S2201"})
  public ModelAndView generateNpe() {
    String oops = null;
    //noinspection DataFlowIssue,ResultOfMethodCallIgnored
    oops.indexOf("oops!");
    //noinspection SpringMVCViewInspection
    return new ModelAndView("hello");
  }

  @ExceptionHandler(NullPointerException.class)
  public ModelAndView handleNpe(NullPointerException ignored) {
    //noinspection SpringMVCViewInspection
    return new ModelAndView("oops")
               .addObject("message",
                          "NullPointerException at %s".formatted(System.currentTimeMillis()));
  }
}
