package com.my.blog.website.model.Bo;

import com.my.blog.website.model.Vo.ContentVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class ArchiveBo implements Serializable{

    private String date;

    private String count;

    private List<ContentVo> articles;

    @Override
    public String toString() {
        return "ArchiveBo{" +
                "date='" + date + '\'' +
                ", count='" + count + '\'' +
                ", articles=" + articles +
                '}';
    }
}
