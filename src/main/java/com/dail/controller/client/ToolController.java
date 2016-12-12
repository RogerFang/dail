package com.dail.controller.client;

import com.dail.model.Tool;
import com.dail.service.NewsService;
import com.dail.service.ToolService;
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
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private ToolService toolService;
    @Autowired
    private NewsService newsService;

    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "4") Integer size,
                       Model model){
        model.addAttribute("toolPage", toolService.page(page, size));
        return "client/tools/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){
        if (id != null){
            Tool tool = toolService.selectByIdWithInfo(id);
            if (tool != null){
                model.addAttribute("tool", tool);
            }
        }
        model.addAttribute("newsPage", newsService.page(1, 5));
        return "client/tools/detail";
    }
}
