package com.hry.spring.mvc.validation;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangrongyou@yixin.im on 2018/3/14.
 */
@RestController
public class ValidationCtrl {
    private static final Logger logger = LoggerFactory
            .getLogger(ValidationCtrl.class);

    /**
     * 正确的请求：
     *  http://127.0.0.1:8080//validation/save?name=name&email=126@126.com&age=18&gender=MALE&birthday=2/22/1985
     *  http://127.0.0.1:8080//validation/save?name=name&email=126@126.com&age=18&gender=MALE&birthday=2/22/1985&phone=13589567201
     * 验证@Size
     *  http://127.0.0.1:8080//validation/save?name=n&email=126@126.com&age=18&gender=MALE&birthday=2/22/1985
     * 验证Eamil为空，输出自定义异常
     *  http://127.0.0.1:8080//validation/save?name=name&email=&age=18&gender=MALE&birthday=2/22/1985&phone=13589567201
     *
        @Valid是javax.validation里的。
        @Validated是@Valid 的一次封装，是spring提供的校验机制使用，有分组验证功能。@Valid不提供分组功能
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/validation/save", method = RequestMethod.GET)
    public Result saveCustomerPage(@Validated CustomerDto model) {
        logger.info("Good" + model.getBirthday());
        Result okResult = new Result();
        okResult.setCode(200);
        okResult.setMessage(JSONObject.toJSON(model).toString());
        return okResult;
    }
}
