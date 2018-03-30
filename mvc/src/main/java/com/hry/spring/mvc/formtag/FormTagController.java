package com.hry.spring.mvc.formtag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 请求URL: http://127.0.0.1:8080//user/info
 */
@Controller
public class FormTagController {
	
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String saveEmployeeAction(User user, Model model
			, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formtag/form";
		}
		model.addAttribute("user", user);
		return "formtag/formSaveSuccess";
	}


	@RequestMapping(value = "/user/info", method = RequestMethod.GET)
	public String infoEmployeeAction(Model model) {
		User user = new User();
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setSex("M");
		
		Preferences p = new Preferences();
		p.setReceiveNewsletter(true);
		p.setFavouriteWord("Magic");
		String[] interests = {"Quidditch",""};
		p.setInterests(interests);
		user.setPreferences(p);
		
		// 默认form tag的值未command
		// model.addAttribute("command", user);
		model.addAttribute("user",user);
		
		// interest2;
		String[] interests2 = {"interests1","interests2"};  
	    model.addAttribute("interestList", interests2); 
		
	    // sexOptions
	    Map<String,String> sexOptions = new HashMap<String,String>();
	    sexOptions.put("M", "男"); 
	    sexOptions.put("F", "女"); 
	    model.addAttribute("sexOptions", sexOptions);
	    
	    // skills
	    List<String> skills = new ArrayList<String>();
	    skills.add("English");
	    user.setSkills(skills);
	    List<String> allSkills = new ArrayList<String>();
	    allSkills.add("Janpanese");
	    allSkills.add("English");
	    allSkills.add("Chinese");
	    model.addAttribute("skills", allSkills);
	    
	    // house
	    user.setHouse("Ravenclaw");
	    
	    //country
	    user.setCountry("UK");
	    List<Country> countryList = new ArrayList<Country>();
	    countryList.add(new Country("AT", "Austria"));
	    countryList.add(new Country("UK", "United Kingdom"));
	    countryList.add(new Country("US", "United States"));
	    model.addAttribute("countryList", countryList);
	    
		return "formtag/form";
	}
}
