package com.dail.controller.admin;

import com.dail.constant.RoleEnum;
import com.dail.model.News;
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
 * Created by Roger on 2016/12/14.
 */
@Controller
@RequestMapping("/system/news")
public class AdNewsController {

    private static final String NEWS_DIR = "news";

    @Autowired
    private NewsService newsService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private SysRoleService sysRoleService;

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "10") Integer size,
                       Model model,
                       HttpSession session) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name())) {
                model.addAttribute("newsPage", newsService.page(page, size));
            }else {
                model.addAttribute("newsPage", newsService.pageByUid(page, size, sessionUid));
            }
        }
        return "admin/news/list";
    }

    @RequiresRoles(value = {"ADMIN","GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("institutions", institutionService.selectAll());
        model.addAttribute("departments", departmentService.selectAll());
        return "admin/news/add";
    }

    @RequiresRoles(value = {"ADMIN","GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(News news,
                      MultipartFile file,
                      HttpSession session) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            if (file != null && !file.isEmpty()) {
                String fileUrl = archiveService.saveMultipartFile(file, NEWS_DIR);
                news.setImgUrl(fileUrl);
            }
            news.setUid(sessionUid);
            newsService.insert(news);
            if (news.getId() != null) {
                return "redirect:/news/detail/" + news.getId();
            }
        }
        return "redirect:/system/news/add";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id,
                       HttpSession session,
                       Model model) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            News news = newsService.selectByPrimaryKey(id);
            if (news != null) {
                if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || news.getUid().equals(sessionUid)) {
                    model.addAttribute("news", news);
                    model.addAttribute("institutions", institutionService.selectAll());
                    model.addAttribute("departments", departmentService.selectAll());
                }
            }
        }
        return "admin/news/edit";
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable Integer id,
                       News news,
                       MultipartFile file,
                       HttpSession session) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null) {
            News ne = newsService.selectByPrimaryKey(id);
            if (ne != null) {
                if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || ne.getUid().equals(sessionUid)) {
                    if (file != null && !file.isEmpty()) {
                        String fileUrl = archiveService.saveMultipartFile(file, NEWS_DIR);
                        news.setImgUrl(fileUrl);
                    }
                    news.setId(id);
                    newsService.update(news);
                }
            }
        }
        return "redirect:/news/detail/" + id;
    }

    @RequiresRoles(value = {"ADMIN", "GENERAL"}, logical = Logical.OR)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity delete(@PathVariable Integer id, HttpSession session) {
        Integer sessionUid = (Integer) session.getAttribute("sessionUid");
        if (sessionUid != null){
            if (id != null) {
                News news = newsService.selectByPrimaryKey(id);
                if (news != null){
                    if (sysRoleService.selectStrByUserId(sessionUid).contains(RoleEnum.ADMIN.name()) || news.getUid().equals(sessionUid)){
                        newsService.deleteByPrimaryKey(id);
                        return new ResponseEntity("Delete successfully", HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity("Fail to delete", HttpStatus.FORBIDDEN);
    }
}
