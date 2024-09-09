package com.cesar31.transaction.infrastructure.config.bean;

import com.cesar31.transaction.application.ports.input.CategoryUseCase;
import com.cesar31.transaction.application.ports.input.ExportUseCase;
import com.cesar31.transaction.application.ports.input.SaleUseCase;
import com.cesar31.transaction.application.ports.output.CategoryOutputPort;
import com.cesar31.transaction.application.ports.output.DishOutputPort;
import com.cesar31.transaction.application.ports.output.CurrentUserOutputPort;
import com.cesar31.transaction.application.ports.output.ExistsClientOutputPort;
import com.cesar31.transaction.application.ports.output.SaleOutputPort;
import com.cesar31.transaction.application.service.CategoryService;
import com.cesar31.transaction.application.service.ExportService;
import com.cesar31.transaction.application.service.SaleService;
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

    @Bean
    SaleUseCase saleService(
            final SaleOutputPort saleOutputPort,
            final CategoryUseCase categoryUseCase,
            final CurrentUserOutputPort currentUserOutputPort,
            final ExistsClientOutputPort existsClientOutputPort,
            final DishOutputPort dishOutputPort
    ) {
        return new SaleService(
                saleOutputPort, categoryUseCase, currentUserOutputPort,
                existsClientOutputPort, dishOutputPort
        );
    }

    @Bean
    ExportUseCase exportService(final SaleUseCase saleUseCase) {
        return new ExportService(saleUseCase);
    }
}
