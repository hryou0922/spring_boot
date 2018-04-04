package com.hry.spring.mvc.reqparameter;

import com.hry.spring.mvc.pojo.VO;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 各种参数传入
 * 
 * @author kk
 *
 */
@Controller
@RequestMapping(value = "/request") // 全局URL
public class RequestParameterController {

	// =================== @PathVariable================================
	/**
	 * 单个 @PathVariable值
	 * 
	 * http://127.0.0.1:8080/request/path/1
	 * 
	 * @param ownerId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/path/{ownerId}")
	public String pathVariable(@PathVariable String ownerId, Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ownerId", ownerId);
		model.addAttribute("map", map);
		return "reqparameter/showInput";
	}

	/**
	 * 多个@PathVariable值
	 * http://127.0.0.1:8080/request/path/1/pet/1234
	 * 
	 * @param ownerId
	 * @param petId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/path/{ownerId}/pet/{petId}")
	public String pathVariable2(@PathVariable String ownerId, @PathVariable String petId, Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ownerId", ownerId);
		map.put("petId", petId);
		model.addAttribute("map", map);
		return "reqparameter/showInput";
	}

	// =================== @PathVariable end =============================
	
	
	// =================== @Matrix end =============================
	// 略
	// =================== Matrix Variables end =============================
	
	

	
	// =================== @RequestParam =============================
	/**
	 * 可以从请求参数中获取参数值
	 * 	http://127.0.0.1:8080/request/requestParam?ownerId=223
	 * 
	 * @param ownerId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/requestParam", method = RequestMethod.GET)
    public String requestParam(@RequestParam("ownerId") int ownerId, ModelMap model) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ownerId", ownerId);
		
		model.addAttribute("map", map);
		return "reqparameter/showInput";
    }
	
	/**
	 * 多个请求参数填入到map
	 * 
	 * http://127.0.0.1:8080/request/requestParam2?ownerId=223&a=4&c=5
	 * 
	 * @param map
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/requestParam2", method = RequestMethod.GET)
    public String requestParam2(@RequestParam Map<String,Object> map, ModelMap model) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("ownerId", ownerId);
		
		model.addAttribute("map", map);
		return "reqparameter/showInput";
    }

	/**
	 * http://127.0.0.1:8080/request/requestParam3
	 * 或
	 * http://127.0.0.1:8080/request/requestParam3?inputStr=myInput
	 *
	 * 设置@RequestParam自定义参数：如设置默认值，是否必须等等
	 * @param inputStr
	 * @return
	 */
	@RequestMapping("/requestParam3")
	public String requestParam3(@RequestParam(value="inputStr", required=true, defaultValue="noInput") String inputStr,
								ModelMap model) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("inputStr",inputStr );
		model.addAttribute("map",map);
		return "reqparameter/showInput";
	}

	// =================== @RequestParam end =============================

	
	// =================== @RequestBody =============================

	/**
	 * @RequestBody: 获取请求的内容
	 *	请求内容为JSON，因为本工程设置请求为json：{"a":1}
	 * @param body
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(value = "/requestBody", method = RequestMethod.POST)
	public String requestBody(@RequestBody String body, ModelMap model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("body",body );
		model.addAttribute("map",map);
		return "reqparameter/showInput";
	}

	// =================== @RequestBody end =============================
	
	
	// =============== HttpEntity ================
	/**
	 * http://127.0.0.1:8080/request/httpEntity
	 * 	HttpEntity
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/httpEntity", method = RequestMethod.GET)
	public String httpEntity(ModelMap model){
		return "reqparameter/httpEntityForm";
	}
	
	@RequestMapping("/httpEntity")
	public String httpEntity2(HttpEntity<byte[]> requestEntity, ModelMap model){
		// 获取header
	    String acceptLanguage = requestEntity.getHeaders().getFirst("Accept-Language");
	    // 获取内容：获取body的内容为空，暂时不知道原因
	    byte[] requestBody = requestEntity.getBody();	  
	    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("acceptLanguage", acceptLanguage);
//		map.put("content", new String(requestBody));
		
		model.addAttribute("map", map);
		return "reqparameter/showInput";
	}
	// ===================HttpEntity end=============================
	
	// =============== @CookieValue ================
	/**
	 * 获取cookie里的值
	 * 	http://127.0.0.1:8080/request/cookieValue
	 * @param cookie
	 * @param model
	 * @return
	 */
	@RequestMapping("/cookieValue")
	public String cookieValue(@CookieValue("JSESSIONID") String cookie,ModelMap model) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cookie", cookie);
		model.addAttribute("map", map);
		return "reqparameter/showInput";
	}

	// ===================@CookieValue end=============================
	
	// =============== @RequestHeader ================
	/**
	 * 获取指定header里的值
	 * 	http://127.0.0.1:8080/request/requestHeader
	 * 	这个无法使用获取cookie值
	 */
	@RequestMapping("/requestHeader")
	public String requestHeader (
			@RequestHeader ("User-Agent") String userAgent,
			@RequestHeader ("Host") String host,
			@RequestHeader ("Cache-Control") String cacheControl,
			ModelMap model) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("User-Agent", userAgent);
		map.put("Host", host);
		map.put("Cache-Control", cacheControl);
		
		model.addAttribute("map", map);
		return "reqparameter/showInput";
	}
	
	/**
	 * 获取所有header封装到Map
	 * 	http://127.0.0.1:8080/request/requestHeaderMap
	 * 
	 * @param map
	 * @param model
	 * @return
	 */
	@RequestMapping("/requestHeaderMap")
	public String requestHeaderMap (@RequestHeader Map<String,String> map,
			ModelMap model) {		
		model.addAttribute("map", map);
		return "reqparameter/showInput";
	}

	// ===================@RequestHeader end=============================
	
	// =============== formModel ================
	/**
	 * http://127.0.0.1:8080/request/formModel
	 * @return
	 */
	@RequestMapping(value="/formModel", method = RequestMethod.GET)
	public String form(){
		return "reqparameter/formModel";
	}
	
	@RequestMapping("/formModel")
	public String formPost(VO vo, ModelMap model){
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", vo.getName());
		map.put("value", vo.getValue());
		map.put("date", vo.getDate());
		
		model.addAttribute("map", map);
		return "reqparameter/showInput";
	}
	// ===================formModel end=============================
	
	// =============== HttpServletRequest + HttpServletResponse ================
	/**
	 * 直接操作 HttpServletRequest 和 HttpServletResponse
	 * 	http://127.0.0.1:8080/request/httpServlet
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/httpServlet")
	public void formPost(HttpServletRequest request, HttpServletResponse response) throws IOException{	    
		String userAgent = request.getHeader("User-Agent");
		String host = request.getHeader("Host");
		String cacheControl = request.getHeader("Cache-Control");
		
		PrintWriter pw = response.getWriter();
		pw.println("User-Agent :"+ userAgent);
		pw.println("Host :" + host);
		pw.println("Cache-Control :" + cacheControl);
		
	}
	// ===================HttpServletRequest + HttpServletResponse end=============================

	// =============== Request Parameters and Header Values ================
	/**
	 * 要进入这个方法，必须带上myParam=myValue
	 * 	http://127.0.0.1:8080/request/reqparameters/1?myParam=myValue
	 * 而以下URL无法进入方法
	 * 	http://127.0.0.1:8080/request/reqparameters/1
	 * 其他条件，也可以这样：
	 * 	 "myParam", "!myParam", or "myParam=myValue"
	 * @param ownerId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/reqparameters/{ownerId}", method = RequestMethod.GET, params="myParam=myValue")
	public String reqParameters(@PathVariable String ownerId, Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ownerId", ownerId);
		model.addAttribute("map", map);
		return "reqparameter/showInput";
	}

	/**
	 * 请求头里必须带上myParam，且值为myValue
	 * 	http://127.0.0.1:8080/request/reqparameters/1
	 *
	 * @param ownerId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/reqparameters/{ownerId}", method = RequestMethod.GET, headers="myParam=myValue")
	public String headerParameters(@PathVariable String ownerId, Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ownerId", ownerId);
		model.addAttribute("map", map);
		return "reqparameter/showInput";
	}
	// =============== Request Parameters and Header Values end ================
}






