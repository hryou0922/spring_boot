package com.hry.spring.mvc.i18n;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class I18nControl {
	private static final Logger logger = LoggerFactory.getLogger(I18nControl.class);
	
	@RequestMapping("/i18n")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println("Welcome home! The client locale is {}." + locale);
        return "i18n/i18n";
    }	
	
}
