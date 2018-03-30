package com.hry.spring.mvc.upload;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	
	/**
	 * http://localhost:8080/upload
	 * 
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadfileUpload(){
		return "upload/upload";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView uploadfileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file)
			throws Exception {
		ModelAndView mo = new ModelAndView();
		mo.setViewName("upload/uploadSuc");
		mo.addObject("name", name);
		if(!file.isEmpty()){
			mo.addObject("originalFileName", file.getOriginalFilename());
			mo.addObject("filename", file.getName());
			mo.addObject("size", file.getSize() / (1024.0 * 1024) + "M");
			mo.addObject("contentType", file.getContentType());
			File saveFile = new File( file.getOriginalFilename());
			System.out.println("获取保存的文件:" + saveFile.getAbsolutePath() );
			FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);  
		}
		return mo;
	}
}