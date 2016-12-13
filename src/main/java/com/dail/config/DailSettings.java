package com.dail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Roger on 2016/12/13.
 */
@Component
@ConfigurationProperties(prefix = "dail", locations = {"classpath:/dail.properties"})
public class DailSettings {

    private String archiveAbsoulutePath;

    public String getArchiveAbsoulutePath() {
        return archiveAbsoulutePath;
    }

    public void setArchiveAbsoulutePath(String archiveAbsoulutePath) {
        this.archiveAbsoulutePath = archiveAbsoulutePath;
    }
}
