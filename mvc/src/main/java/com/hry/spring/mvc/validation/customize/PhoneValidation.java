package com.hry.spring.mvc.validation.customize;

import javax.validation.Payload;
import javax.validation.constraints.Min;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by huangrongyou@yixin.im on 2018/3/14.
 */
public @interface PhoneValidation {
    String message() default "{value}不是正确的手机号码";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * @return value the element must be higher or equal to
     */
    String value();

//    /**
//     * Defines several {@link Min} annotations on the same element.
//     *
//     * @see Min
//     */
//    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
//    @Retention(RUNTIME)
//    @Documented
//    @interface List {
//
//        Min[] value();
//    }
}
