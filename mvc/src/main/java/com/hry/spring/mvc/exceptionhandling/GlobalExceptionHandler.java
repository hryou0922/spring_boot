package com.hry.spring.mvc.exceptionhandling;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.hry.spring.mvc.exceptionhandling.support.ExceptionJSONInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 全局异常处理异常
 * 
 * @ControllerAdvice
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 处理全局SQLException
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex) {
		logger.info("SQLException Occured:: URL=" + request.getRequestURL());
		return "exceptionhandling/database_error";
	}

	/**
	 * For IOException, we are returning void with status code as 404, so our
	 * error-page will be used in this case.
	 * 
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException() {
		logger.error("IOException handler executed");
		// returning 404 error code
	}

	@ExceptionHandler(EmployeeExJsonException.class)
	public @ResponseBody
	ExceptionJSONInfo handleEmployeeNotFoundException(HttpServletRequest request, Exception ex) {

		ExceptionJSONInfo response = new ExceptionJSONInfo();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(ex.getMessage());

		return response;
	}
}