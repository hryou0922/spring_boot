package com.hry.spring.mvc.validation;

import com.hry.spring.mvc.validation.customize.PhoneValidation;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;


/**
 *
 * Created by huangrongyou@yixin.im on 2018/3/14.
 */
public class CustomerDto {
    @Size(min=2, max=30)
    private String name;

    // 自定义错误信息
    @NotEmpty(message = "自定义错误信息，Email不能为空")
    @Email
    private String email;

    @NotNull
    @Min(18) @Max(100)
    private Integer age;

    @NotNull
    private Gender gender;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
    private Date birthday;

    // 自定义规则注解
    @PhoneValidation
    private String phone;

    public enum Gender {
        MALE, FEMALE
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
