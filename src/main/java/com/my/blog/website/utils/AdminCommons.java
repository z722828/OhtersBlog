package com.my.blog.website.utils;

import com.my.blog.website.model.Vo.MetaVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 后台公共函数
 */
@Component
public class AdminCommons {

    /**
     * 判断category 和 cats的交集  （category : 类型，类别，部门）
     * @param category
     * @param cats
     * @return
     */
    public static boolean exist_cat(MetaVo category, String cats) {
        String[] arr = StringUtils.split(cats, ",");
        if (null != arr && arr.length > 0) {
            for (String c : arr) {
                if (c.trim().equals(category.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final String[] COLORS = {"default", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    public static String rand_color() {
        int r = Tools.rand(0,COLORS.length-1);
        return COLORS[r];
    }

}
