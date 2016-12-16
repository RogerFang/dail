package com.dail.controller.admin;

import com.dail.constant.RoleEnum;
import com.dail.model.Project;
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

/**
 * Created by Roger on 2016/12/13.
 */
@Controller
@RequestMapping("/system/projects")
public class AdProjectController {

    private static String PROJECTS_DIR = "projects";

    @Autowired
    private ProjectService projectService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private ArchiveService archiveService;

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "8") Integer size,
                       HttpSession session,
                       Model model){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name())){
                model.addAttribute("projectPage", projectService.page(page, size));
            }else {
                model.addAttribute("projectPage", projectService.pageByUid(sessionUid, page, size));
            }
        }
        return "admin/projects/list";
    }

    @RequiresRoles(value = {"ADMIN","GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("institutions", institutionService.selectAll());
        model.addAttribute("departments", departmentService.selectAll());
        model.addAttribute("notparticipants", peopleService.selectAll());
        return "admin/projects/add";
    }

    @RequiresRoles(value = {"ADMIN","GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Project project,
                      MultipartFile file,
                      HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            if (file!=null && !file.isEmpty()){
                String fileUrl = archiveService.saveMultipartFile(file, PROJECTS_DIR);
                project.setImgUrl(fileUrl);
            }
            project.setUid(sessionUid);
            projectService.insert(project);
        }
        if (project.getId() != null){
            return "redirect:/projects/detail/"+project.getId();
        }
        return "admin/projects/add";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, HttpSession session, Model model){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            Project project = projectService.selectByIdWithDetail(id);
            if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || project.getUid().equals(sessionUid)){
                model.addAttribute("project", project);
                model.addAttribute("institutions", institutionService.selectAll());
                model.addAttribute("departments", departmentService.selectAll());
                model.addAttribute("notparticipants", peopleService.selectAllNotParticipants(project.getParticipants()));
            }
        }
        return "admin/projects/edit";
    }

    @RequiresRoles(value = {"ADMIN","GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable Integer id,
                       Project project,
                       MultipartFile file,
                       HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            Project pro = projectService.selectByPrimaryKey(id);
            if (pro != null){
                if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || pro.getUid().equals(sessionUid)){
                    if (file!=null && !file.isEmpty()){
                        String fileUrl = archiveService.saveMultipartFile(file, PROJECTS_DIR);
                        project.setImgUrl(fileUrl);
                    }
                    project.setId(id);
                    projectService.update(project);
                }
            }
        }
        return "redirect:/projects/detail/"+id;
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/delete/{id}" ,method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity delete(@PathVariable Integer id, HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            if (id != null) {
                Project project = projectService.selectByPrimaryKey(id);
                if (project != null){
                    if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || project.getUid().equals(sessionUid)){
                        projectService.deleteByPrimaryKey(id);
                        return new ResponseEntity("Delete successfully", HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity("Fail to delete project", HttpStatus.FORBIDDEN);
    }
}
