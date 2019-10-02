package by.epam.crackertracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.epam.controller")
public class WebConfig extends WebMvcConfigurerAdapter{

    public static final int ORDER = 1;
    public static final String SUFFIX = ".ftl";
    public static final String PREFIX = "";
    public static final String INDEX_PATH = "/";
    public static final String JSP_PATH = "/WEB-INF/views/";

    @Bean
    public ViewResolver getViewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setOrder(ORDER);
        freeMarkerViewResolver.setSuffix(SUFFIX);
        freeMarkerViewResolver.setPrefix(PREFIX);
        return freeMarkerViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths(INDEX_PATH, JSP_PATH);
        return freeMarkerConfigurer;
    }

}
