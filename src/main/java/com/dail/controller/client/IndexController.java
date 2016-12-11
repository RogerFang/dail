package com.dail.controller.client;

import com.dail.service.NewsService;
import com.dail.service.ProjectService;
import com.dail.service.SlideService;
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
    private ProjectService projectService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private SlideService slideService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("slides", slideService.selectAllEnabled(Boolean.TRUE));
        model.addAttribute("projectPage", projectService.page(1, 10));
        model.addAttribute("newsPage", newsService.page(1, 10));
        return "client/index";
    }
}
