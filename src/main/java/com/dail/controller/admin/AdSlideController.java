package com.dail.controller.admin;

import com.dail.config.DailSettings;
import com.dail.model.Slide;
import com.dail.model.SysConst;
import com.dail.service.ArchiveService;
import com.dail.service.SlideService;
import com.dail.service.SysConstService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by Roger on 2016/12/11.
 */
@Controller
@RequiresRoles("ADMIN")
@RequestMapping("/system/slide")
public class AdSlideController {

    private static final String SLIDE_DIR = "slide";

    @Autowired
    private SlideService slideService;
    @Autowired
    private SysConstService sysConstService;
    @Autowired
    private ArchiveService archiveService;

    @RequestMapping
    public String list(@RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "10") Integer size,
                       Model model) {
        model.addAttribute("slidePage", slideService.page(page, size));
        model.addAttribute("count", sysConstService.selectByPrimaryKey(1).getIndexSlideCount());
        return "admin/slide/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "admin/slide/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Slide slide,
                      MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String fileUrl = archiveService.saveMultipartFile(file, SLIDE_DIR);
            slide.setImgUrl(fileUrl);
        }
        slide.setCreateTime(new Date());
        slide.setEnabled(true);
        slideService.insertSelective(slide);
        return "redirect:/system/slide";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("slide", slideService.selectByPrimaryKey(id));
        return "admin/slide/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable Integer id,
                       Slide slide,
                       MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String fileUrl = archiveService.saveMultipartFile(file, SLIDE_DIR);
            slide.setImgUrl(fileUrl);
        }
        slide.setEnabled(true);
        slide.setId(id);
        slideService.updateByPrimaryKeySelective(slide);
        return "redirect:/system/slide";
    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public String set(Integer count) {
        if (count != null && count > 0) {
            SysConst sysConst = sysConstService.selectByPrimaryKey(1);
            sysConst.setIndexSlideCount(count);
            sysConstService.updateByPrimaryKeySelective(sysConst);
        }
        return "redirect:";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity delete(@PathVariable Integer id) {
        slideService.deleteByPrimaryKey(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }
}
