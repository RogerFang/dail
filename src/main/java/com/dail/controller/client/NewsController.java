package com.dail.controller.client;

import com.dail.model.News;
import com.dail.service.NewsService;
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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "20") Integer size,
                       Model model){
        model.addAttribute("newsPage", newsService.page(page, size));
        return "client/news/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Integer id,
                         Model model){
        if (id != null){
            News news = newsService.selectByPrimaryKeyWithDetail(id);
            if (news != null){
                model.addAttribute("news", news);
            }
        }
        model.addAttribute("newsPage", newsService.page(1, 5));
        return "client/news/detail";
    }
}
