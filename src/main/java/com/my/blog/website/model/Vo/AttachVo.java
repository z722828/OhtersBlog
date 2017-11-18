package com.my.blog.website.model.Vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Attach:  附件
 */
@Data
public class AttachVo implements Serializable {

    private Integer id;

    private String fname;

    private String ftype;

    private String fkey;

    private Integer authorId;

    private Integer created;
}
