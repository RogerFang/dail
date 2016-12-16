package com.dail.controller.admin;

import com.dail.model.Department;
import com.dail.service.DepartmentService;
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
@RequestMapping("/system/dep")
@RequiresRoles("ADMIN")
public class AdDepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping
    public String list(Model model){
        model.addAttribute("departments", departmentService.selectAll());
        return "admin/department/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Department department){
        department.setCreateTime(new Date());
        departmentService.insertSelective(department);
        return "redirect:/system/dep";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity delete(@PathVariable Integer id){
        departmentService.deleteByPrimaryKey(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity edit(Department department){
        departmentService.updateByPrimaryKeySelective(department);
        return new ResponseEntity<>("Edit successfully!", HttpStatus.OK);
    }
}
