package com.dail.controller.admin;

import com.dail.model.People;
import com.dail.service.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Created by Roger on 2016/12/12.
 */
@Controller
@RequestMapping("/system/people")
public class AdPeopleController {

    @Autowired
    private PeopleService peopleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ResearchDirectionService researchDirectionService;

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            People people = sysUserService.selectByIdWithPeople(sessionUid).getPeople();
            model.addAttribute("people", people);
        }
        model.addAttribute("institutions", institutionService.selectAll());
        model.addAttribute("departments", departmentService.selectAll());
        model.addAttribute("researchDirections", researchDirectionService.selectAll());
        return "admin/people/generaledit";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(People people, MultipartFile file){
        return "";
    }

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/edit/{peopleId}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer peopleId){
        return "admin/people/adminedit";
    }

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/edit/{peopleId}", method = RequestMethod.POST)
    public String edit(@PathVariable Integer peopleId, People people, MultipartFile file){
        return "";
    }

}
