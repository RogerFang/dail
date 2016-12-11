package com.dail.controller.admin;

import com.dail.service.SlideService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Roger on 2016/12/11.
 */
@RequiresRoles("ADMIN")
@Controller
@RequestMapping("/system/slide")
public class SlideController {

    @Autowired
    private SlideService slideService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("slides", slideService.selectAll());
        return "admin/slide/list";
    }
}
