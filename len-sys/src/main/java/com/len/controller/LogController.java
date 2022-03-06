package com.len.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.len.base.BaseController;
import com.len.entity.SysLog;
import com.len.mapper.SysLogMapper;
import com.len.util.LenResponse;
import com.len.util.MsHelper;
import com.len.util.ReType;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhuxiaomeng
 * @date 2017/12/29.
 * @email lenospmiller@gmail.com
 * 
 *        日志监控
 */
@Controller
@RequestMapping(value = "/log")
@Slf4j
@Api(value = "日志管理", tags = "操作日志记录")
public class LogController extends BaseController {
    @Autowired
    private SysLogMapper logMapper;

    @GetMapping(value = "showLog")
    public String showMenu(Model model) {
        return "/system/log/logList";
    }

    /**
     * 日志监控
     *
     * @param sysLog
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "showLogList")
    @ResponseBody
    public ReType showLog(SysLog sysLog, String page, String limit) {
        Page<SysLog> tPage = PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
        return new ReType(tPage.getTotal(), logMapper.selectListByPage(sysLog));
    }

    /**
     * 删除log
     *
     * @param
     * @return
     */
    @PostMapping(value = "del")
    @ResponseBody
    public LenResponse del(String[] ids) {
        for (String id : ids) {
            logMapper.deleteById(Integer.valueOf(id));
        }
        return succ(MsHelper.getMsg("del.success"));
    }

}
