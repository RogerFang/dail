package com.dail.controller.admin;

import com.dail.service.ToolService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by Roger on 2016/12/15.
 */
@Controller
@RequestMapping("/system/tools")
public class AdToolController {

    @Autowired
    private ToolService toolService;

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "10") Integer size,
                       HttpSession session,
                       Model model){
        return "admin/tools/list";
    }
}
