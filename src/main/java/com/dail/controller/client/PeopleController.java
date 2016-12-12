package com.dail.controller.client;

import com.dail.model.People;
import com.dail.service.PeopleService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "10") Integer size,
                       Model model) {
        PageInfo<People> o = peopleService.pageWithInfo(page, size);
        model.addAttribute("peoplePage", o);
        return "client/people/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){
        if (id != null){
            People people = peopleService.selectByIdWithDetail(id);
            if (people != null){
                model.addAttribute("people", people);
            }
        }
        return "client/people/detail";
    }
}
