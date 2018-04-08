package com.hry.spring.mvc.aop.log.manager.model;

import java.util.Date;

/**
 * 日志信息类
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public class LogAdmModel {
    private Long id;
    private String userId;
    private String userName;
    private String admModel;
    private String admEvent;
    private String admEntity;
    private Date createDate;
    private String admOptContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdmModel() {
        return admModel;
    }

    public void setAdmModel(String admModel) {
        this.admModel = admModel;
    }

    public String getAdmEvent() {
        return admEvent;
    }

    public void setAdmEvent(String admEvent) {
        this.admEvent = admEvent;
    }

    public String getAdmEntity() {
        return admEntity;
    }

    public void setAdmEntity(String admEntity) {
        this.admEntity = admEntity;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAdmOptContent() {
        return admOptContent;
    }

    public void setAdmOptContent(String admOptContent) {
        this.admOptContent = admOptContent;
    }
}
