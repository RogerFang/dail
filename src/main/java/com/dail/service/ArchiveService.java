package com.dail.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Roger on 2016/12/13.
 */
public interface ArchiveService {

    String saveMultipartFile(MultipartFile file, String subdir);
}
