package com.dail.service.impl;

import com.dail.config.DailSettings;
import com.dail.service.ArchiveService;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Roger on 2016/12/13.
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveServiceImpl.class);

    @Autowired
    private DailSettings dailSettings;

    /**
     * save upload file
     * @param file upload file
     * @param subdir sub directory
     * @return fileUrl: relative to root directory, example: '/people/1.jpg'
     */
    @Override
    public String saveMultipartFile(MultipartFile file, String subdir) {
        String dir = getDirectoryPath(subdir);
        checkDirectory(dir);
        String uuidFilename = uuidFileName(file.getOriginalFilename());
        try {
            /*BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(new File(dir), uuidFilename)));
            bos.write(file.getBytes());*/
            Files.write(file.getBytes(), new File(new File(dir), uuidFilename));
            return File.separator + subdir + File.separator + uuidFilename;
        } catch (IOException e) {
            String msg = "Error while saving file";
            LOGGER.error(msg, e);
            throw new RuntimeException(msg, e);
        }

    }

    private String getDirectoryPath(String subdir){
        String archiveAbsoulutePath = dailSettings.getArchiveAbsoulutePath();
        return archiveAbsoulutePath+subdir;
    }

    private void checkDirectory(String dir){
        File file = new File(dir);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    private String uuidFileName(String filename){
        return UUID.randomUUID().toString() +"."+ Files.getFileExtension(filename);
    }
}
