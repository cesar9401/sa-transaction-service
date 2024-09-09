package com.cesar31.transaction.infrastructure.adapters.output.feignclient;

import com.cesar31.transaction.application.dto.DishDto;
import com.cesar31.transaction.application.dto.UpdateDishStockReqDto;
import com.cesar31.transaction.application.ports.output.DishOutputPort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "sa-organization")
public interface DishFeignClientAdapter extends DishOutputPort {

    @Override
    @GetMapping("/api/sa-organization/dishes")
    List<DishDto> checkStock(@RequestParam(name = "dishIds", required = false) String dishIds);

    @Override
    @PutMapping("/api/sa-organization/dishes/update-stock")
    List<UUID> updDishStock(@RequestBody UpdateDishStockReqDto reqDto);
}
