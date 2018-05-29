package com.hry.swagger.ctl;

import com.hry.swagger.ctl.domain.Student;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by huangrongyou@yixin.im on 2018/5/15.
 */
@RestController
@EnableSwagger2 // 启动swagger注解
// api-value：定义名称，如果没有定义，则默认显示类名
@Api(value = "Swagger Test", description = "Rest API for test operations", tags = "test API")
public class SwaggerTestCtl {

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/queryById", method = {RequestMethod.POST, RequestMethod.GET})
    // 定义请求参数
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "id", value = "主键", required = true) })
    public String queryById(String id){
        return "test";
    }

    @ApiOperation(value = "Display greeting message to non-admin user", response = Student.class)
    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET})
    public Student add(@ApiParam(value="用户参数") Student student){
        return new Student();
    }


    @RequestMapping(value = "/add2", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiResponses({
            @ApiResponse(code = 400, message = "服务器内部异常"),
            @ApiResponse(code = 500, message = "权限不足") })
    public int add2(Student student){
        return 1;
    }
}
