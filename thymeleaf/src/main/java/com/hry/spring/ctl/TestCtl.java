package com.hry.spring.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestCtl {
	
	@RequestMapping("/")
    public String index() {
        return "index";  
    }
	
	
    @RequestMapping("/simple")
    public String simpleIndex(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://blog.didispace.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "simple/index";  
    }
}