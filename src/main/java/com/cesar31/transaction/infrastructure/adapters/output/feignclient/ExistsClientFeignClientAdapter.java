package com.cesar31.transaction.infrastructure.adapters.output.feignclient;

import com.cesar31.transaction.application.ports.output.ExistsClientOutputPort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "sa-root")
public interface ExistsClientFeignClientAdapter extends ExistsClientOutputPort {

    @Override
    @GetMapping("/api/sa-root/clients/exists/{clientId}")
    Boolean existsClientById(@PathVariable("clientId") UUID clientId);
}
