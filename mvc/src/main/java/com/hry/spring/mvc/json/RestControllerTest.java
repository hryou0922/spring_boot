package com.hry.spring.mvc.json;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class RestControllerTest {


    /**
     * 方法返回对象
     *  http://127.0.0.1:8080/rest/vo
     * @return
     */
    @RequestMapping(value = "/vo", method = RequestMethod.GET)
    public VO getUser() {
        VO vo = new VO();
        vo.setName("name");
        vo.setValue("value");
        return vo;
    }

    /**
     *
     * 直接返回内容
     *  http://localhost:8080/rest/string
     * @return
     */
    @RequestMapping(value = "/string", method = RequestMethod.GET)
    // @ResponseStatus(HttpStatus.OK)  
    public String getUserHtml() {
        return "{'example':'---'}";
    }
}

class VO {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
