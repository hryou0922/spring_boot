package com.hry.spring.mvc.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Employee Not Found") //404
public class EmployeeExNotFoundException extends Exception {

  private static final long serialVersionUID = -3332292346834265371L;

  public EmployeeExNotFoundException(int id){
    super("EmployeeNotFoundException with id="+id);
  }
}