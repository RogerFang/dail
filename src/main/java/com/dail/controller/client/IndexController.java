package com.dail.controller.client;

import com.dail.model.SysConst;
import com.dail.service.NewsService;
import com.dail.service.ProjectService;
import com.dail.service.SlideService;
import com.dail.service.SysConstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Roger on 2016/12/11.
 */
@Controller
public class IndexController {

    @Autowired
    private SysConstService sysConstService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private SlideService slideService;

    @RequestMapping("/")
    public String index(Model model){
        SysConst sysConst = sysConstService.selectByPrimaryKey(1);
        model.addAttribute("slidePage", slideService.pageEnabled(Boolean.TRUE, 1, sysConst.getIndexSlideCount()));
        model.addAttribute("projectPage", projectService.page(1, sysConst.getIndexProjectCount()));
        model.addAttribute("newsPage", newsService.page(1, sysConst.getIndexNewsCount()));
        return "client/index";
    }
}
