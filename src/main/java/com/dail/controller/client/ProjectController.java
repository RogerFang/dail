package com.dail.controller.client;

import com.dail.model.Project;
import com.dail.service.NewsService;
import com.dail.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Roger on 2016/12/12.
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private NewsService newsService;

    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "5") Integer size,
                       Model model){
        model.addAttribute("projectPage",   projectService.page(page, size));
        model.addAttribute("newsPage", newsService.page(1, 5));
        return "client/projects/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){
        if (id != null){
            Project project = projectService.selectByIdWithInfo(id);
            if (project != null){
                model.addAttribute("project", project);
            }
        }
        return "client/projects/detail";
    }
}
