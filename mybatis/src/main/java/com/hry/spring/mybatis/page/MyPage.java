package com.hry.spring.mybatis.page;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Collections;
import java.util.List;

/**
 * 分布类
 * Created by Administrator on 2017/9/6.
 */
public class MyPage<T> {
    @JSONField(ordinal = 1)
    private Integer code = 200;// 状态码，默认状态

    @JSONField(ordinal = 2)
    private String message = "";// 提示消息或者错误消息

    @JSONField(ordinal = 3)
    private String apiId = "";// 请求的唯一标识，预留

    @JSONField(ordinal = 4)
    private Integer totalCount = 0;//记录总数

    @JSONField(ordinal = 5)
    private List<T> rows = Collections.emptyList();//本次返回的数据列表

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
