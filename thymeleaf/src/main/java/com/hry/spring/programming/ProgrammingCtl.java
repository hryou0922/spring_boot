package com.hry.spring.programming;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hry.spring.support.User;

/**
 * 编程语法 1. i
 * 
 * @author hry
 *
 */
@Controller
@RequestMapping("/programming")
public class ProgrammingCtl {

	@RequestMapping("programming")
	public String iteration(ModelMap modeMap) {
		// Iteration
		List<User> userList = new ArrayList<User>();
		userList.add(new User("son_1", true, "other_1", 11));
		userList.add(new User("son_2", false, "other_2", 22));
		userList.add(new User("son_3", true, "other_3", 33));
		userList.add(new User("son_4", false, "other_4", 44));
		modeMap.put("userList", userList);
		
		// ifelse
		User userIf = new User("admin", true, "other_if", 11);
		modeMap.put("user", userIf);
		
		return "programming/programming";
	}
}
