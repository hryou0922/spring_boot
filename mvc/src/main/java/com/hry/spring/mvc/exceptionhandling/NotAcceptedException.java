package com.hry.spring.mvc.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 通过使用@ResponseStatus注解异常
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="Employee Not Found") //404
public class NotAcceptedException extends Exception {

  private static final long serialVersionUID = -3332292346834265371L;

  public NotAcceptedException(int id){
    super("EmployeeNotFoundException with id="+id);
  }
}