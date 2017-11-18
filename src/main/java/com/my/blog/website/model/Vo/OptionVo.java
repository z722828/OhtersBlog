package com.my.blog.website.model.Vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OptionVo implements Serializable {

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置值
     */
    private String value;

    private String description;

    private static final long serialVersionUID = 1L;

}
