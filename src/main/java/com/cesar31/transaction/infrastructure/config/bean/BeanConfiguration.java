package com.cesar31.transaction.infrastructure.config.bean;

import com.cesar31.transaction.application.ports.input.CategoryUseCase;
import com.cesar31.transaction.application.ports.output.CategoryOutputPort;
import com.cesar31.transaction.application.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = BeanConfiguration.class)
public class BeanConfiguration {

    @Bean
    CategoryUseCase categoryService(final CategoryOutputPort outputPort) {
        return new CategoryService(outputPort);
    }
}
