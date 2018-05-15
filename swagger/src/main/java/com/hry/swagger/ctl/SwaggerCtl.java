package com.hry.swagger.ctl;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by huangrongyou@yixin.im on 2018/5/15.
 */
@RestController
@EnableSwagger2 // 启动swagger注解
public class SwaggerCtl {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
