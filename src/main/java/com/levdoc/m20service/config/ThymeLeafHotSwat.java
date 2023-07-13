package com.levdoc.m20service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeLeafHotSwat {

    private final ThymeleafProperties thymeleafProperties;

    public ThymeLeafHotSwat(ThymeleafProperties thymeleafProperties) {
        this.thymeleafProperties = thymeleafProperties;
    }

    @Value("${spring.thymeleaf.templates_root}")
    private String templatesRoot;

    @Bean
    public ITemplateResolver defaultTemplateResolver() {
        FileTemplateResolver fileTemplateResolver = new FileTemplateResolver();

        fileTemplateResolver.setSuffix(thymeleafProperties.getSuffix());
        fileTemplateResolver.setPrefix(templatesRoot);
        fileTemplateResolver.setTemplateMode(thymeleafProperties.getMode());
        fileTemplateResolver.setCacheable(thymeleafProperties.isCache());

        return fileTemplateResolver;
    }

}
