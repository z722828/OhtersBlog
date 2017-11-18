package com.my.blog.website.interceptor;

import com.my.blog.website.utils.AdminCommons;
import com.my.blog.website.utils.Commons;
import com.my.blog.website.utils.MapCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;

/**
 * 自定义拦截器
 */
public class BaseInterceptor implements HandlerInterceptor{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);

    private static final String USER_AGENT = "user-agent";

    @Resource
    private IUserService userService;

    private MapCache cache = MapCache.single();

    @Resource
    private Commons commons;

    @Resource
    private AdminCommons adminCommons;
}
