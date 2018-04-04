package com.hry.spring.mvc.async;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@RequestMapping(value="/async")
public class DeferredResultController {
	
	@RequestMapping("/dr")
	@ResponseBody
	public DeferredResult<String> deferredResult(Locale locale, Model model) {
		DeferredResult<String> deferredResult = new DeferredResult<String>();
	    // Save the deferredResult somewhere..
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				waiting(deferredResult);
			}
		}).start();
		System.out.println("--dealing... method end");
	    return deferredResult;
    }
	
	private void waiting(DeferredResult<String> deferredResult ){
		try {
			System.out.println("--dealing...");
			Thread.sleep(1000 * 5);
			deferredResult.setResult("aa");
			System.out.println("--dealing... end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
