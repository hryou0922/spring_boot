package com.hry.spring.mvc.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    /*** 
     * 用户登陆 
     * <p>注解配置，只允许POST提交到该方法 
     * @param username 
     * @param password 
     * @return 
     */  
    @RequestMapping(value="/login/login",method=RequestMethod.POST)  
    public ModelAndView login(String username,String password){  
        //验证传递过来的参数是否正确，否则返回到登陆页面。  
        if(this.checkParams(new String[]{username,password})){  
            //指定要返回的页面为succ.jsp  
            ModelAndView mav = new ModelAndView("/login/succ");  
            //将参数返回给页面  
            mav.addObject("username",username);  
            mav.addObject("password", password);  
            return mav;  
        }  
        return new ModelAndView("/login/home");  
    }

    /**
     * 登录页面：http://127.0.0.1:8080//login/login
     *
     * @return
     */
    @RequestMapping(value="/login/form",method=RequestMethod.GET)  
    public String login(){
    	return "/login/form";
    }
      
    /*** 
     * 验证参数是否为空 
     * @param params 
     * @return 
     */  
    private boolean checkParams(String[] params){  
        for(String param:params){  
            if(param==""||param==null||param.isEmpty()){  
                return false;  
            }  
        }  
        return true;  
    }  
}
