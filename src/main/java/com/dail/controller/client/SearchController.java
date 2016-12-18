package com.dail.controller.client;

import com.dail.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Roger on 2016/12/18.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private ToolService toolService;
    @Autowired
    private PublicationService publicationService;

    @RequestMapping
    public String search(@RequestParam(required = false, defaultValue = "news") String type,
                         String query,
                         @RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "10") Integer size,
                         Model model){
        if (type.equals("project")){
            model.addAttribute("projectPage", projectService.search(page,size,query));
        }else if (type.equals("people")){
            model.addAttribute("peoplePage", peopleService.search(page,size,query));
        }else if (type.equals("tool")){
            model.addAttribute("toolPage", toolService.search(page,size,query));
        }else if (type.equals("pub")){
            model.addAttribute("pubPage", publicationService.search(page,size,query));
        }else{
            model.addAttribute("newsPage", newsService.search(page,size,query));
        }
        model.addAttribute("type", type);
        model.addAttribute("query", query);
        return "client/search";
    }
}
