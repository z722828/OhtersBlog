package com.my.blog.website.model.Bo;

import com.my.blog.website.model.Vo.CommentVo;
import lombok.Data;

import java.util.List;

/**
 * 返回页面的评论， 包含父子评论内容
 */
@Data
public class CommentBo extends CommentVo {

    private int levels;

    private List<CommentVo> children;
}
