package com.dail.controller.admin;

import com.dail.model.People;
import com.dail.model.Result;
import com.dail.model.SysUser;
import com.dail.service.PeopleService;
import com.dail.service.SysUserRoleService;
import com.dail.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Roger on 2016/12/11.
 */
@RequiresRoles("ADMIN")
@Controller
@RequestMapping("/system/user")
public class AdSysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private PeopleService peopleService;

    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "10") Integer size,
                       Model model){
        model.addAttribute("userPage", sysUserService.page(page, size));
        model.addAttribute("peopleList", peopleService.selectAll());
        return "admin/user/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@ModelAttribute SysUser sysUser){
        return "admin/user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerHandle(@ModelAttribute SysUser sysUser){
        sysUserService.register(sysUser);
        return "redirect:";
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public Result role(Integer uid, Integer rid){
        sysUserRoleService.changeRole(uid, rid);
        Result result = new Result(200);
        return result;
    }

    @RequestMapping("/people")
    @ResponseBody
    public PageInfo<People> people(@RequestParam(required = false, defaultValue = "1") Integer page,
                                   @RequestParam(required = false, defaultValue = "10") Integer size){
        return peopleService.pageWithInfo(page, size);
    }
}
