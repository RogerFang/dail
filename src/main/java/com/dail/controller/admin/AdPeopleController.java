package com.dail.controller.admin;

import com.dail.constant.RoleEnum;
import com.dail.model.People;
import com.dail.model.SysUser;
import com.dail.service.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Roger on 2016/12/12.
 */
@Controller
@RequestMapping("/system/people")
public class AdPeopleController {

    private static final String PEOPLE_DIR = "people";

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
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private SysRoleService sysRoleService;

    @RequiresRoles("ADMIN")
    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "12") Integer size,
                       Model model) {
        model.addAttribute("peoplePage", peopleService.pageWithInfoUser(page, size));
        return "admin/people/list";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            Integer peopleId = sysUserService.selectById(sessionUid).getPeopleId();
            if (peopleId != null) {
                model.addAttribute("people", peopleService.selectByIdWithInfoUser(peopleId));
                model.addAttribute("institutions", institutionService.selectAll());
                model.addAttribute("departments", departmentService.selectAll());
                model.addAttribute("researchDirections", researchDirectionService.selectAll());
            }
        }
        return "admin/people/edit";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id,
                       Model model,
                       HttpSession session) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            SysUser user = sysUserService.selectById(sessionUid);
            if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || user.getPeopleId() == id) {
                model.addAttribute("people", peopleService.selectByIdWithInfoUser(id));
                model.addAttribute("institutions", institutionService.selectAll());
                model.addAttribute("departments", departmentService.selectAll());
                model.addAttribute("researchDirections", researchDirectionService.selectAll());
            }
        }
        return "admin/people/edit";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable Integer id,
                       People people,
                       MultipartFile file,
                       HttpSession session) throws IOException {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            SysUser user = sysUserService.selectById(sessionUid);
            if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || user.getPeopleId() == id) {
                if (file != null && !file.isEmpty()) {
                    String fileUrl = archiveService.saveMultipartFile(file, PEOPLE_DIR);
                    people.setImgUrl(fileUrl);
                }
                people.setId(id);
                peopleService.update(people);
            }
        }
        return "redirect:/people/detail/" + people.getId();
    }

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("institutions", institutionService.selectAll());
        model.addAttribute("departments", departmentService.selectAll());
        model.addAttribute("researchDirections", researchDirectionService.selectAll());
        return "admin/people/add";
    }

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(People people, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String fileUrl = archiveService.saveMultipartFile(file, PEOPLE_DIR);
            people.setImgUrl(fileUrl);
        }
        peopleService.insert(people);
        return "redirect:/people/detail/" + people.getId();
    }

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity delete(@PathVariable Integer id) {
        if (peopleService.deleteByPrimaryKey(id) > 0) {
            return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail to delete", HttpStatus.FORBIDDEN);
    }
}
