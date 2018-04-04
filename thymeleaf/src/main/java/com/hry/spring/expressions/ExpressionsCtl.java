package com.hry.spring.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hry.spring.support.Family;
import com.hry.spring.support.User;

/**
 * 标准表达式语法
 * @author hry
 *
 */
@Controller
@RequestMapping("/expressions")
public class ExpressionsCtl {
	
	/**
	 * 表达式
	 */
	@RequestMapping("/simple")
	public String simpleExpressions(ModelMap map){
		// 变量表达式：Variable Expressions
		User user = new User("simple_name");
		user.setAge(new Random().nextInt(100));
		map.put("user", user);
		return "expressions/simple2";
	} 
	
	/**
	 * 复杂对象
	 * @param modeMap
	 * @return
	 */
	@RequestMapping("/complex")
	public String complex(ModelMap modeMap){
		// 复杂对象
		Family family = new Family();
		family.setFather(new User("father"));
		List<User> childList = new ArrayList<User>();
		childList.add(new User("son_1"));
		childList.add(new User("son_2"));
		family.setChildList(childList);
		modeMap.put("family", family);
		// map
		HashMap<String, User> hashMap = new HashMap<String, User>();
		hashMap.put("hashMapKey", new User("hashMap_name"));
		modeMap.put("hashMap", hashMap);
		
		return "expressions/complex";
	}
	
	/**
	 * 表达式工具对象:Expression Utility Objects
	 * 
	 */
	@RequestMapping("/utility")
	public String utility(ModelMap modeMap){
		return "expressions/utility";
	}
	
	/**
	 * 处理URL
	 * 
	 * @param modeMap
	 * @return
	 */
	@RequestMapping("/url")
	public String url(ModelMap modeMap){
		modeMap.put("id", "123");
		return "expressions/url";
	}
}
















