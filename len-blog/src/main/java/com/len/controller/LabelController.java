package com.len.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.len.base.BaseController;
import com.len.entity.BlogLabel;
import com.len.service.BlogLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2018/7/28.
 * @email 154040976@qq.com
 * <p>
 * 标签
 */
@RestController
@RequestMapping("/blog")
public class LabelController extends BaseController {

    @Autowired
    private BlogLabelService blogLabelService;

    private static String[] color = {"green", "red", "yellow", "blue"};

    /**
     * 获取标签 后期增加规则 颜色后期处理成浅色 好看
     *
     * @return
     */
    @GetMapping("/getLabel")
    public JSONArray label() {
        List<BlogLabel> blogLabels = blogLabelService.selectAll();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(blogLabels));
        int i = 0;
        JSONObject object;
        for (Object o : array) {
            i = i == 4 ? 0 : i;
            object = (JSONObject) o;
            object.put("color", color[i]);
            object.remove("id");
            i++;
        }
        return array;
    }
}
