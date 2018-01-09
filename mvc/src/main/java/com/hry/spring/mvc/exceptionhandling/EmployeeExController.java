package com.hry.spring.mvc.exceptionhandling;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.hry.spring.mvc.exceptionhandling.support.EmployeeEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EmployeeExController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeExController.class);

	/**
	 *
	 * http://127.0.0.1:8080/emp/1
	 * 	演示当前@Controller类中定义@ExceptionHandler的用法
	 * 
	 * http://127.0.0.1:8080/emp/2
	 * 	演示全局配置定义拦截ExceptionHandler(SQLException.class)
	 *
	 * http://127.0.0.1:8080/emp/3
	 * 	演示全局配置定义拦截捕获IOException，并返回404错误
	 *
	 * http://127.0.0.1:8080/emp/4
	 * 	演示全局配置定义拦截捕获EmployeeExJsonException，并返回一个JSON字符串
	 *
	 * 	http://127.0.0.1:8080/emp/5
	 * 	演示@ResponseStatus另一种用法：捕获NotAcceptedException，抛出406
	 *
	 * http://127.0.0.1:8080/emp/10
	 * 	演示正常的情况，返回一个字符串
	 *
	 * http://127.0.0.1:8080/emp/20
	 * 	演示没有拦截的异常，走默认处理流程
	 *
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String getEmployee(@PathVariable("id") int id, Model model) throws Exception {
		// deliberately throwing different types of exception
		if (id == 1) {
			throw new EmployeeExNotFoundException(id);
		} else if (id == 2) {
			throw new SQLException("SQLException, id=" + id);
		} else if (id == 3) {
			throw new IOException("IOException, id=" + id);
		} else if (id == 4) {
			throw new EmployeeExJsonException(id);
		} else if( id==5){
			throw new NotAcceptedException(5);
		} else if (id == 10) {
			EmployeeEx emp = new EmployeeEx();
			emp.setName("Pankaj");
			emp.setId(id);
			model.addAttribute("employee", emp);
			return "home";
		} else {
			throw new Exception("Generic Exception, id=" + id);
		}

	}

	/**
	 * 此方法只处理本类抛出的 EmployeeNotFoundException 异常
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(EmployeeExNotFoundException.class)
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex) {
		logger.error("Requested URL=" + request.getRequestURL());
		logger.error("Exception Raised=" + ex);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());

		modelAndView.setViewName("exceptionhandling/error");
		return modelAndView;
	}
}
