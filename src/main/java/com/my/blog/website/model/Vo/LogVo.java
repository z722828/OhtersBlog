package com.my.blog.website.model.Vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogVo implements Serializable {
    /**
     * 日志主键
     */
    private Integer id;

    /**
     * 产生的动作
     */
    private String action;

    /**
     * 产生的数据
     */
    private String data;

    /**
     * 发生人id
     */
    private Integer authorId;

    /**
     * 日志产生的ip
     */
    private String ip;

    /**
     * 日志创建的时间
     */
    private Integer created;

    private static final long serialVersionUID = 1L;


}
