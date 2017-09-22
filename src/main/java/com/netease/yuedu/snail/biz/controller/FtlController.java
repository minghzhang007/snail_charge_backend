package com.netease.yuedu.snail.biz.controller;

import com.netease.yuedu.snail.biz.dao.snail.ReadTimeTradeMapper;
import com.netease.yuedu.snail.biz.model.queryObject.ReadTimeTradeQueryObject;
import com.netease.yuedu.snail.biz.model.vo.ReadTimeTradeVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ftl")
public class FtlController {
    @Resource
    private ReadTimeTradeMapper tradeMapper;


    @Value("${application.message:Hello World}")
    private String message="Hello World";

    @RequestMapping(value = "/{type}/home", method = RequestMethod.GET)
    public ModelAndView showJspHome(@PathVariable(value = "type") String type) {
        ModelAndView model = new ModelAndView();
        model.addObject("name", "帅帅");
        if ("jsp".equals(type)) {
            //加入类型路径，从而可以通过viewNames来判断选择视图对应的解析器
            model.setViewName("jsp/home");
        } else if ("thymeleaf".equals(type)) {
            model.setViewName("thymeleaf/home");
        } else if ("ftl".equals(type)) {
            model.setViewName("ftl/home");
        } else {
            model.setViewName("error");
        }
        return model;
    }

    @RequestMapping("/hello")
    public String welcome(Model model){
        model.addAttribute("time",new Date());
        model.addAttribute("message",this.message);
        model.addAttribute("name","倾国倾城!");
        model.addAttribute("price",90);
        List<ReadTimeTradeVO> retList = new ArrayList<>(10);
        List<ReadTimeTradeVO> readTimeTradeVOS = tradeMapper.queryReadTimeRecords(new ReadTimeTradeQueryObject());
        for (int i = 0; i < 10; i++) {
            retList.add(readTimeTradeVOS.get(i));
        }
        model.addAttribute("list",retList);
        return "ftl/home";
    }
}
