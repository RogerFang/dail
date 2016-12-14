package com.dail.config.web;

import com.dail.config.DailSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Roger on 2016/12/13.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private DailSettings dailSettings;

    /**
     * 静态资源设置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // url pattern: /** 会覆盖系统默认配置,需要自己添加 'classpath:/static/'

        // url pattern: /archive/**; 磁盘绝对路径(absolute path): file:D:/mydirectory/
        registry.addResourceHandler("/**")
                    .addResourceLocations("file:" + dailSettings.getArchiveAbsoulutePath())
                    .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }
}
