package com.hry.spring.mvc.exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此类只演示HandlerExceptionResolver的用法
 * 	通过接口Ordered的getOrder定义此HandlerExceptionResolver的优先级最高
 *  只打印错误信息，不进行任何处理。
 * 接口：Ordered 定义顺序HandlerExceptionResolver
 *
 */
@Component // 需要带上此注解
public class MySimpleHandlerExceptionResolver implements HandlerExceptionResolver,Ordered{
	private static final Logger logger = LoggerFactory.getLogger(MySimpleHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.info("url = {}, exception={}, exception message = {}", request.getRequestURI(), ex.getClass().getCanonicalName(), ex.getMessage());
		// 返回null，让后面HandlerExceptionResolver继续进行处理；如果不让后面的HandlerExceptionResolver进行处理，则这里返回一个ModelAndView对象即可
		return null;
	}
	public int getOrder(){
		// 表示此HandlerExceptionResolver的优先级最高
		return Ordered.HIGHEST_PRECEDENCE;
	}
}
