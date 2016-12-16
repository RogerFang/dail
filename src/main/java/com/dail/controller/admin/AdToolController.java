package com.dail.controller.admin;

import com.dail.constant.RoleEnum;
import com.dail.model.Tool;
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
 * Created by Roger on 2016/12/15.
 */
@Controller
@RequestMapping("/system/tools")
public class AdToolController {

    private static String TOOLS_DIR = "tools";

    @Autowired
    private ToolService toolService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private DepartmentService departmentService;
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
                model.addAttribute("toolPage", toolService.page(page, size));
            }else {
                model.addAttribute("toolPage", toolService.pageByUid(page, size, sessionUid));
            }
        }
        return "admin/tools/list";
    }

    @RequiresRoles(value = {"ADMIN","GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("institutions", institutionService.selectAll());
        model.addAttribute("departments", departmentService.selectAll());
        return "admin/tools/add";
    }

    @RequiresRoles(value = {"ADMIN","GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Tool tool,
                      MultipartFile file,
                      HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            if (file!=null && !file.isEmpty()){
                String fileUrl = archiveService.saveMultipartFile(file, TOOLS_DIR);
                tool.setImgUrl(fileUrl);
            }
            tool.setUid(sessionUid);
            toolService.insert(tool);
        }
        if (tool.getId() != null){
            return "redirect:/tools/detail/"+tool.getId();
        }
        return "admin/tools/add";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, HttpSession session, Model model){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            Tool tool = toolService.selectByIdWithInfoUser(id);
            if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || tool.getUid().equals(sessionUid)){
                model.addAttribute("tool", tool);
                model.addAttribute("institutions", institutionService.selectAll());
                model.addAttribute("departments", departmentService.selectAll());
            }
        }
        return "admin/tools/edit";
    }

    @RequiresRoles(value = {"ADMIN","GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable Integer id,
                       Tool tool,
                       MultipartFile file,
                       HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            Tool to = toolService.selectByPrimaryKey(id);
            if (to != null){
                if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || to.getUid().equals(sessionUid)){
                    if (file!=null && !file.isEmpty()){
                        String fileUrl = archiveService.saveMultipartFile(file, TOOLS_DIR);
                        tool.setImgUrl(fileUrl);
                    }
                    tool.setId(id);
                    toolService.update(tool);
                }
            }
        }
        return "redirect:/tools/detail/"+id;
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/delete/{id}" ,method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity delete(@PathVariable Integer id, HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            if (id != null) {
                Tool tool = toolService.selectByPrimaryKey(id);
                if (tool != null){
                    if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || tool.getUid().equals(sessionUid)){
                        toolService.deleteByPrimaryKey(id);
                        return new ResponseEntity("Delete successfully", HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity("Fail to delete project", HttpStatus.FORBIDDEN);
    }
}