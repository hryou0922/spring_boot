package com.hry.spring.mvc.validation.customize;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义规则校验的类
 * Created by huangrongyou@yixin.im on 2018/3/14.
 */
@Documented
// 指定真正实现校验规则的类
@Constraint(validatedBy = PhoneValidationValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneValidation {
    String message() default "不是正确的手机号码";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PhoneValidation[] value();
    }
}
