package com.hry.spring.mvc.resparameter;

import com.hry.spring.mvc.pojo.ModelAttributeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/modelAttribute")
public class ModelAttributeController {
	// ================ 以下两个方法是相同 ========================
	/**
	 * http://127.0.0.1:8080/modelAttribute/query
	 * 	创建ModelAttributeVO对象
	 * 
	 * query 和 query2 方法是相同的。
	 * 	两者 都在model里产生一个key为“modelAttributeVO”，value为modelAttributeVO
	 * @param modelAttributeVO
	 * @return
	 */
	@RequestMapping("/query")  
	public String query(ModelAttributeVO modelAttributeVO) {
		modelAttributeVO.setName("U love me");
	    return "resparameter/modelAttributeShow";  
	}
	
	/**
	 * 
	 * http://127.0.0.1:8080/modelAttribute/query2
	 * 
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping("/query2")
	public String query2(Model model, String name) {  
		ModelAttributeVO modelAttributeVO=new ModelAttributeVO();
		modelAttributeVO.setName(name);
	    model.addAttribute("modelAttributeVO",modelAttributeVO);  
	     
	    return "resparameter/modelAttributeShow"; 
	} 
	// ================ 以下两个方法是相同 end========================
	
	// ================ 将@ModelAttribute作用在方法上 ================
	/**
	 * 此方法是写在ModelAttributeController内,那么执行ModelAttributeController内带有@RequestMapping的方法之前,
	 * 都会先执行此addModelAttribute方法.并且执行addModelAttribute过程中会添加两个对象到model,
	 * 	先将key为"modelAttributeVO"的对象(由addModelAttribute方法的ModelAttributeVO modelAttribute引起的),
	 * 	再添加key为"modelAttributeVO11"的对象(由注解@ModelAttribute("modelAttributeVO1")引起的).
	 * 
	 *
	 * @return
	 */
	@ModelAttribute("modelAttributeVO1")  
	public ModelAttributeVO addModelAttribute(
	//		ModelAttributeVO modelAttribute
	) {
		ModelAttributeVO vo = new ModelAttributeVO();
		vo.setName("name:modelAttribute");
		vo.setValue("value:modelAttribute");
	    return vo;
	}  
	// ================ 将@ModelAttribute作用在方法上 end ================
	
	// ================ 将@ModelAttribute作用在方法上参数上 ================
	/**
	 * 此方法会先从model去获取key为"modelAttributeVO"的对象,如果获取不到会通过反射实例化一个ModelAttributeVO对象,
	 * 再从request里面拿值set到这个对象,然后把这个ModelAttributeVO对象添加到model(其中key为"modelAttributeVO").
	 * 使用了@ModelAttribute可修改这个key,不一定是"modelAttributeVO"
	 * 
	 * 	http://127.0.0.1:8080//modelAttribute/modelAttributeParam
	 * 
	 * @param modelAttributeVO
	 * @return
	 */
	@RequestMapping("/modelAttributeParam")  
	public String modelAttributeParam(@ModelAttribute ModelAttributeVO modelAttributeVO) {  
		modelAttributeVO.setName("user default key name");  
	    return "resparameter/modelAttributeShow"; 
	}
	
	/**
	 * 此方法会先从model去获取key为"modelAttributeVO1"的对象
	 * 	http://127.0.0.1:8080//modelAttribute/modelAttributeParam2
	 * @param modelAttributeVO
	 * @return
	 */
	@RequestMapping("/modelAttributeParam2")  
	public String modelAttributeParam2(@ModelAttribute("modelAttributeVO1") ModelAttributeVO modelAttributeVO) {  
		modelAttributeVO.setName("use special key name");  
	    return "resparameter/modelAttributeShow"; 
	} 
	// ================ 将@ModelAttribute作用在方法上参数上 end ================
	
	
}
