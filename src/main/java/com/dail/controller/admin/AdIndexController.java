package com.dail.controller.admin;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Roger on 2016/12/16.
 */
@Controller
@RequestMapping("/system")
@RequiresRoles("ADMIN")
public class AdIndexController {

    @RequestMapping
    public String index(){
        return "admin/index";
    }
}
