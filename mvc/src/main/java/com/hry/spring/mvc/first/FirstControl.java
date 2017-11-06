package com.hry.spring.mvc.first;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstControl {
	
	/**
	 * http://127.0.0.1:8080/first
	 * 	返回页面 META-INF/resources/WEB-INF/page/first/index.jsp
	 * @param model
	 * @return
	 */
	@RequestMapping("/first")
    public String first(Model model) {
        model.addAttribute("message", "Hello World!");
        return "first/index";
    }	
}
