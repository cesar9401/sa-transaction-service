package com.cesar31.transaction.application.ports.output;

import com.cesar31.transaction.domain.DishOrder;
import com.cesar31.transaction.domain.Payment;
import com.cesar31.transaction.domain.Sale;

import java.util.List;

public interface SaleOutputPort {

    Sale save(Sale sale, List<DishOrder> orders, List<Payment> payments);
}
