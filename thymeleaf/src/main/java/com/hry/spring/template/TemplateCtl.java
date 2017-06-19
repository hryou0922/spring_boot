package com.hry.spring.template;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateCtl {

	
	@RequestMapping("/template")
	public String template(ModelMap modelMap){
		Calendar c =Calendar.getInstance();
		modelMap.put("month", c.get(Calendar.MONTH) + 1);
		modelMap.put("date", c.get(Calendar.DAY_OF_MONTH));
		return "template/template";
	}
}