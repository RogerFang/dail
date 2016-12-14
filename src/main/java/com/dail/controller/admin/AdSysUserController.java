package com.dail.controller.admin;

import com.dail.model.Result;
import com.dail.model.SysUser;
import com.dail.service.PeopleService;
import com.dail.service.SysUserRoleService;
import com.dail.service.SysUserService;
import com.google.common.base.Strings;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Roger on 2016/12/11.
 */
@Controller
@RequestMapping("/system/user")
public class AdSysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private PeopleService peopleService;

    @RequiresRoles("ADMIN")
    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "20") Integer size,
                       Model model) {
        model.addAttribute("userPage", sysUserService.page(page, size));
        return "admin/user/list";
    }

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(SysUser sysUser, RedirectAttributes redirectAttributes) {
        if (sysUserService.selectByUsername(sysUser.getUsername()) != null) {
            // username existed
            redirectAttributes.addFlashAttribute("result", new Result(444));
        } else {
            sysUserService.register(sysUser);
            redirectAttributes.addFlashAttribute("result", new Result(666));
        }
        return "redirect:";
    }

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String resetPassword(SysUser sysUser, RedirectAttributes redirectAttributes) {
        if (sysUserService.selectById(sysUser.getId()) != null) {
            sysUserService.resetPassword(sysUser);
            redirectAttributes.addFlashAttribute("result", new Result(888));
        } else {
            redirectAttributes.addFlashAttribute("result", new Result(999));
        }
        return "redirect:";
    }

    /*@RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public Result role(Integer uid, Integer rid){
        sysUserRoleService.changeRole(uid, rid);
        Result result = new Result(200);
        return result;
    }*/

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/bind/{id}", method = RequestMethod.GET)
    public String people(@RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "30") Integer size,
                         @PathVariable Integer id,
                         String query,
                         Model model) {
        model.addAttribute("user", sysUserService.selectByIdWithPeople(id));
        model.addAttribute("query", query);
        model.addAttribute("peoplePage", peopleService.pageWithInfoUserLike(page, size, query));
        return "admin/user/bind";
    }

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/bind/{id}", method = RequestMethod.POST)
    public String people(@PathVariable Integer id, Integer peopleId) {
        if (peopleId != null){
            sysUserService.bind(id, peopleId);
        }
       return "redirect:"+id;
    }

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity delete(@PathVariable Integer id){
        sysUserService.deleteByPrimaryKey(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }
}
