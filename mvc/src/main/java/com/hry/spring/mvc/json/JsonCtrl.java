package com.hry.spring.mvc.json;

import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonCtrl {
	
	@RequestMapping(name="/json")
	public JsonVo json(){
		JsonVo vo = new JsonVo();
		vo.setName("name");
		vo.setAge(new Random().nextInt(100));
		return vo;
	}
}
