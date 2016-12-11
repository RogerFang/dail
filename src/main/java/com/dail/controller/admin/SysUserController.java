package com.dail.controller.admin;

import com.dail.model.Result;
import com.dail.model.SysUser;
import com.dail.service.SysUserRoleService;
import com.dail.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Roger on 2016/12/11.
 */
@RequiresRoles("admin")
@Controller
@RequestMapping("/system/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping("/list")
    public String list(Model model){
        return "admin/user/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@ModelAttribute SysUser sysUser){
        return "admin/user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerHandle(@ModelAttribute SysUser sysUser){
        sysUserService.register(sysUser);
        return "redirect:list";
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public Result role(Integer uid, Integer rid){
        sysUserRoleService.changeRole(uid, rid);
        Result result = new Result(200);
        return result;
    }
}
