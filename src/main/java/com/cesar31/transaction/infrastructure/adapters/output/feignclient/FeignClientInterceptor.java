package com.cesar31.transaction.infrastructure.adapters.output.feignclient;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Slf4j
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            log.warn("Attributes are null");
            return;
        }

        var request = attributes.getRequest();
        var authorization = request.getHeader("Authorization");
        if (authorization == null) {
            log.warn("Authorization header is empty");
            return;
        }

        log.info("propagating jwt...");
        requestTemplate.header("Authorization", authorization);
    }
}
