package com.dail.controller.admin;

import com.dail.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roger on 2016/12/13.
 */
@Controller
@RequestMapping("/img")
public class AdImgController {

    private static Map<String, String> TYPE_MAP = new HashMap<>();
    static {
        TYPE_MAP.put("news", "img/news");
        TYPE_MAP.put("projects", "img/projects");
        TYPE_MAP.put("people", "img/people");
    }

    @Autowired
    private ArchiveService archiveService;

    @RequestMapping(value = "/upload/{type}", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file,
                         @PathVariable String type){
        if (file!=null && !file.isEmpty()){
            return archiveService.saveMultipartFile(file, TYPE_MAP.get(type));
        }else {
            return "";
        }
    }
}
