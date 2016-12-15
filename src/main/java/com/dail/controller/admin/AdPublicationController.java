package com.dail.controller.admin;

import com.dail.constant.RoleEnum;
import com.dail.model.People;
import com.dail.model.Publication;
import com.dail.model.SysUser;
import com.dail.service.PeopleService;
import com.dail.service.PublicationService;
import com.dail.service.SysRoleService;
import com.dail.service.SysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Roger on 2016/12/15.
 */
@Controller
@RequestMapping("/system/pubs")
public class AdPublicationController {

    @Autowired
    private PublicationService publicationService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private SysRoleService sysRoleService;

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping("/for")
    public String listForPeople(@RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "20") Integer size,
                                HttpSession session,
                                Model model) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            SysUser user = sysUserService.selectById(sessionUid);
            if (user.getPeopleId() != null) {
                People people = peopleService.selectByPrimaryKey(user.getPeopleId());
                if (people != null) {
                    model.addAttribute("people", people);
                    model.addAttribute("publicationPage", publicationService.pageByPeopleId(page, size, people.getId()));
                }
            }
        }
        return "admin/publication/list";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping("/for/{peopleId}")
    public String listForPeople(@PathVariable Integer peopleId,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "20") Integer size,
                                HttpSession session,
                                Model model) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            SysUser user = sysUserService.selectById(sessionUid);
            if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || user.getPeopleId().equals(peopleId)) {
                People people = peopleService.selectByPrimaryKey(peopleId);
                if (people != null) {
                    model.addAttribute("people", people);
                    model.addAttribute("publicationPage", publicationService.pageByPeopleId(page, size, peopleId));
                }
            }
        }
        return "admin/publication/list";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/add/{peopleId}", method = RequestMethod.POST)
    public String add(@PathVariable Integer peopleId,
                      Publication publication,
                      HttpSession session,
                      Model model) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            SysUser user = sysUserService.selectById(sessionUid);
            if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || user.getPeopleId().equals(peopleId)) {
                publication.setPeopleId(peopleId);
                publication.setCreateTime(new Date());
                publicationService.insertSelective(publication);
            }
        }
        return "redirect:/system/pubs/for/"+peopleId;
    }
}
