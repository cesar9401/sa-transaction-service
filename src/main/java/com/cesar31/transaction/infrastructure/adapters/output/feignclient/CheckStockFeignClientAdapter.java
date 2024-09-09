package com.cesar31.transaction.infrastructure.adapters.output.feignclient;

import com.cesar31.transaction.application.dto.DishDto;
import com.cesar31.transaction.application.ports.output.CheckStockOutputPort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "sa-organization")
public interface CheckStockFeignClientAdapter extends CheckStockOutputPort {

    @Override
    @GetMapping("/api/sa-organization/dishes")
    List<DishDto> checkStock(@RequestParam(name = "dishIds", required = false) String dishIds);
}
