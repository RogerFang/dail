package com.dail.controller.admin;

import com.dail.model.Institution;
import com.dail.service.InstitutionService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Roger on 2016/12/15.
 */
@Controller
@RequestMapping("/system/ins")
@RequiresRoles("ADMIN")
public class AdInstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @RequestMapping
    public String list(Model model){
        model.addAttribute("institutions", institutionService.selectAll());
        return "admin/institution/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Institution institution){
        institution.setCreateTime(new Date());
        institutionService.insertSelective(institution);
        return "redirect:";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity delete(@PathVariable Integer id){
        institutionService.deleteByPrimaryKey(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity edit(Institution institution){
        institutionService.updateByPrimaryKeySelective(institution);
        return new ResponseEntity<>("Edit successfully!", HttpStatus.OK);
    }
}
