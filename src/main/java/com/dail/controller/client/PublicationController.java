package com.dail.controller.client;

import com.dail.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Roger on 2016/12/12.
 */
@Controller
@RequestMapping("/pubs")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "10") Integer size,
                       Model model){
        model.addAttribute("pubPage", publicationService.page(page, size));
        return "client/publications/list";
    }
}
