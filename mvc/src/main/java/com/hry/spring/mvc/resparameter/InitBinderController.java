package com.hry.spring.mvc.resparameter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("initBinder")
public class InitBinderController {
	
	/**
	 * 此绑定只对输入的属性通过指定的CustomDateEditor执行解析，
	 * 但是不对返回客户端的属性进行解析
	 * 
	 * @param binder
	 */
    @InitBinder//必须有一个参数WebDataBinder  
    public void initBinder(WebDataBinder binder) {
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    	// 注释此句：对特定的属性，进行解析
    //	binder.registerCustomEditor(Date.class, "shortDate",new CustomDateEditor(new SimpleDateFormat("yyyy-MM"), false));
        binder.registerCustomEditor(Integer.class, new IntegerEditor());
        binder.registerCustomEditor(Float.class, new FloatEditor());
    }
    
    /**
     *
     * http://127.0.0.1:8080/initBinder/test/2106-11-22?in=&shortDate=2016-01-02
     * http://127.0.0.1:8080//initBinder/test/2106-11-22?in=1&shortDate=2016-01-02
     * 
     * @param date
     * @param in
     * @param model
     * @return
     */
	@RequestMapping("test/{date}")
	public String test(@PathVariable Date date, @RequestParam Integer in,
			@RequestParam("shortDate") Date shortDate, ModelMap model) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", date);
		map.put("in", in);
		map.put("shortDate", shortDate);
		model.addAttribute("map", map);
		
		return "resparameter/showInput";
	}
}

// IntegerEditor
class IntegerEditor extends PropertiesEditor {    
    @Override    
    public void setAsText(String text) throws IllegalArgumentException {    
        if (text == null || text.equals("")) {    
            text = "0";    
        }    
        setValue(Integer.parseInt(text));    
    }    
    
    @Override    
    public String getAsText() {    
        return getValue().toString();    
    }    
}  

// FloatEditor
class FloatEditor extends PropertiesEditor {    
    @Override    
    public void setAsText(String text) throws IllegalArgumentException {    
        if (text == null || text.equals("")) {    
            text = "0";    
        }    
        setValue(Float.parseFloat(text));    
    }    
    
    @Override    
    public String getAsText() {    
        return getValue().toString();    
    }    
}  

