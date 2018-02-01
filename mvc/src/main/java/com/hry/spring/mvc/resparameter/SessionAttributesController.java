package com.hry.spring.mvc.resparameter;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller  
@RequestMapping("sessionAttributes")  
@SessionAttributes("test")
public class SessionAttributesController {
	
	// ========================================================
	/**
	 * 
	 * 在类级别上使用@SessionAttributes("consumer"),它的作用是在controller共享 model 属性,
	 * 直到调用org.springframework.web.bind.support.SessionStatus#setComplete会清除此session值.
	 * 否则长期保留(session过期,这个值也不再保留).
	 * 因此先调用/user/test1,再调用/user/test2.在test2方法能得到test1方法put进去的值.
	 * 当移除上面的注解,因为属性为test的session也被清除了,所以test2方法会得到是null.
	 * 
	 * http://127.0.0.1:8080/sessionAttributes/test1
	 * 
	 * 	org.springframework.web.bind.support.SessionStatus status handle for marking form processing as complete, 
	 * 	which triggers the cleanup of session attributes that have been indicated by the @SessionAttributes 
	 * 	annotation at the handler type level.
	 * @param model
	 * @param sessionStatus
	 * @return
	 */
    @RequestMapping(value = "test1")  
    public String test1(Map<String, Object> model, SessionStatus sessionStatus) {  
        model.put("test","something");
        // 如果不注释这个代码，则会清理session里的值
        // sessionStatus.setComplete();
        return "resparameter/sessionAttributeShow";  
    }  
  
    /**
     * http://127.0.0.1:8080/sessionAttributes/test2
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "test2")  
    public String test2(Map<String,?>  model) {  
        Object test = model.get("test");  
        System.out.println(test);  
        return "resparameter/sessionAttributeShow"; 
    }  
    
    // ========================================================
}
