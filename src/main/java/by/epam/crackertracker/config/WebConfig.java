package by.epam.crackertracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.epam.crackertracker")
public class WebConfig extends WebMvcConfigurerAdapter{

    public static final int ORDER = 1;
    public static final String SUFFIX = ".ftl";
    public static final String PREFIX = "";
    public static final String INDEX_PATH = "/";
    public static final String JSP_PATH = "/WEB-INF/views/";
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String ENCODING = "text/html;charset=utf-8";
    public static final String RESOURCE_BUNDLE = "message";
    public static final String RESOURCE_PATH = "/**";
    public static final String PICTURE_PATH = "/picture";
    public static final String UUI_PATH = "/uui";

//    @Autowired
//    private DataSource dataSource;

    @Bean
    public ViewResolver getViewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setOrder(ORDER);
        freeMarkerViewResolver.setSuffix(SUFFIX);
        freeMarkerViewResolver.setPrefix(PREFIX);
        freeMarkerViewResolver.setContentType(ENCODING);
        return freeMarkerViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths(INDEX_PATH, JSP_PATH);
        freeMarkerConfigurer.setDefaultEncoding(DEFAULT_ENCODING);
        return freeMarkerConfigurer;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames(RESOURCE_BUNDLE);
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding(DEFAULT_ENCODING);
        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCE_PATH).addResourceLocations(PICTURE_PATH, UUI_PATH);
    }

//    @Bean
//    public PlatformTransactionManager txManager() {
//        return new DataSourceTransactionManager(dataSource);
//    }

    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver =
                new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");
        mappings.setProperty("InvalidCreditCardException", "creditCardError");
        mappings.put("org.springframework.web.servlet.PageNotFound", "404");

        resolver.setExceptionMappings(mappings);  // None by default
        resolver.setDefaultErrorView("error");    // No default
        resolver.setExceptionAttribute("ex");
        // Default is "exception"
        resolver.setWarnLogCategory("example.MvcLogger");     // No default
        return resolver;
    }


}
