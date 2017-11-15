package com.my.blog.website.controller.admin;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.model.Vo.AttachVo;
import com.my.blog.website.service.IAttachService;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.utils.TaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 附件管理
 */
@Controller
@RequestMapping("admin/attach")
public class AttachController {

    private static final Logger LOGGER = LoggerFactory.getLogger("AttachController.class");

    public static final String CLASSPATH = TaleUtils.getUplodFilePath();

    @Resource
    private IAttachService attachService;

    @Resource
    private ILogService logService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request,
                        @RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "12") int limit) {
        PageInfo<AttachVo> attachPaginator = attachService.getAttachs(page, limit);
        request.setAttribute("attachs", attachPaginator);
        request.setAttribute(Types.Attach_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType), Commons.site_url());
        request.setAttribute("max_file_size", WebConst.MAX_FILE_SIZE / 1024);
        return "admin/attach";
    }
}
