package com.cesar31.transaction.application.service;

import com.cesar31.transaction.application.dto.DishDto;
import com.cesar31.transaction.application.dto.SaleReqDto;
import com.cesar31.transaction.application.exception.ApplicationException;
import com.cesar31.transaction.application.exception.EntityNotFoundException;
import com.cesar31.transaction.application.exception.ForbiddenException;
import com.cesar31.transaction.application.ports.input.CategoryUseCase;
import com.cesar31.transaction.application.ports.input.SaleUseCase;
import com.cesar31.transaction.application.ports.output.CheckStockOutputPort;
import com.cesar31.transaction.application.ports.output.CurrentUserOutputPort;
import com.cesar31.transaction.application.ports.output.ExistsClientOutputPort;
import com.cesar31.transaction.application.util.enums.CategoryEnum;
import com.cesar31.transaction.application.util.enums.RoleEnum;
import com.cesar31.transaction.domain.Category;
import com.cesar31.transaction.domain.DishOrder;
import com.cesar31.transaction.domain.Payment;
import com.cesar31.transaction.domain.Sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class SaleService implements SaleUseCase {

    private final CategoryUseCase categoryUseCase;
    private final CurrentUserOutputPort currentUserOutputPort;
    private final ExistsClientOutputPort existsClientOutputPort;
    private final CheckStockOutputPort checkStockOutputPort;

    private final Set<UUID> allowedRoles = Set.of(RoleEnum.HOTEL_MANAGER.roleId, RoleEnum.RESTAURANT_MANAGER.roleId, RoleEnum.ROOT.roleId);

    public SaleService(CategoryUseCase categoryUseCase, CurrentUserOutputPort currentUserOutputPort, ExistsClientOutputPort existsClientOutputPort, CheckStockOutputPort checkStockOutputPort) {
        this.categoryUseCase = categoryUseCase;
        this.currentUserOutputPort = currentUserOutputPort;
        this.existsClientOutputPort = existsClientOutputPort;
        this.checkStockOutputPort = checkStockOutputPort;
    }

    @Override
    public Sale createSale(SaleReqDto saleReqDto) throws ForbiddenException, ApplicationException, EntityNotFoundException {
        saleReqDto.validateSelf();

        var hasAnyOfAllowedRoles = currentUserOutputPort.hasAnyRole(allowedRoles);
        if (!hasAnyOfAllowedRoles) throw new ForbiddenException("not_allowed_to_perform_a_sale");

        var transactions = saleReqDto.getTransactions();
        var payments = saleReqDto.getPayments();
        if (transactions.isEmpty() && payments.isEmpty()) throw new ApplicationException("invalid_transaction");

        var saleId = saleReqDto.getSaleId();
        if (saleId != null) throw new ApplicationException("not_implemented_yet.");

        var clientId = saleReqDto.getClientId();
        var existsClient = existsClientOutputPort.existsClientById(clientId);
        if (!existsClient) throw new EntityNotFoundException("client_not_found");

        var catPaymentMethods = categoryUseCase.findByParentId(CategoryEnum.PAYMENT_METHOD.categoryId)
                .stream()
                .collect(Collectors.toMap(Category::getCategoryId, cat -> cat));

        for (var payment : payments) {
            if (!catPaymentMethods.containsKey(payment.getCatPaymentMethod()))
                throw new ApplicationException("invalid_payment_method");
        }

        var dishesIds = transactions
                .stream()
                .map(SaleReqDto.DishOrderReqDto::getDishId)
                .map(UUID::toString)
                .distinct()
                .toList();

        // TODO: check stock
        var dishes = checkStockOutputPort.checkStock(String.join(",", dishesIds));

        var requestDishes = transactions
                .stream()
                .collect(Collectors.toMap(SaleReqDto.DishOrderReqDto::getDishId, SaleReqDto.DishOrderReqDto::getAmount, Integer::sum));

        var stockDishes = dishes
                .stream()
                .collect(Collectors.toMap(DishDto::getDishId, dish -> dish));

        var now = LocalDateTime.now();

        var saleTotal = BigDecimal.ZERO;// TODO: add discount here
        var dishOrders = new ArrayList<DishOrder>();
        for (var reqDishes : requestDishes.entrySet()) {
            var dishId = reqDishes.getKey();
            var amount = reqDishes.getValue();

            if (!stockDishes.containsKey(dishId)) throw new EntityNotFoundException("dish_not_found: " + dishId);
            var dish = stockDishes.get(dishId);
            if (dish.getStock() < amount) throw new ApplicationException("not_enough_stock_for_dish: " + dishId);

            var price = dish.getPrice();
            var total = price.multiply(new BigDecimal(amount));

            var dishOrder = new DishOrder();
            dishOrder.setTransactionId(UUID.randomUUID());
            dishOrder.setFoodOrderDescription(String.format("%d orden(es) de %s", amount, dish.getName()));
            dishOrder.setQuantity(amount);
            dishOrder.setUnitPrice(price);
            dishOrder.setUnitDiscount(BigDecimal.ZERO);
            dishOrder.setTransactionTotal(total);
            dishOrder.setDiscountTotal(BigDecimal.ZERO);
            dishOrder.setNetTotal(total);// TODO: minus discount
            dishOrder.setEntryDate(now);
            dishOrder.setDishId(dishId);

            dishOrders.add(dishOrder);
            saleTotal = saleTotal.add(total);
        }

        var netPayment = BigDecimal.ZERO;
        var salePayments = new ArrayList<Payment>();
        for (var reqPayment : payments) {
            var amount = reqPayment.getAmount();
            var payment = new Payment();
            payment.setPaymentId(UUID.randomUUID());
            payment.setNetTotal(amount);
            payment.setEntryDate(now);
            payment.setCatPaymentMethod(catPaymentMethods.get(reqPayment.getCatPaymentMethod()));

            netPayment = netPayment.add(amount);
            salePayments.add(payment);
        }

        int compareTo = netPayment.compareTo(saleTotal);
        Category catSaleStatus;
        if (compareTo > 0) throw new ApplicationException("invalid_net_payment");
        else if (compareTo < 0) catSaleStatus = categoryUseCase.findBy(CategoryEnum.SS_PENDING_PAYMENT.categoryId);
        else catSaleStatus = categoryUseCase.findBy(CategoryEnum.SS_COMPLETED.categoryId);

        var sale = new Sale();
        sale.setClientId(clientId);
        sale.setEntryDate(now);
        sale.setTotalTransactionSum(saleTotal);
        sale.setTotalDiscountSum(BigDecimal.ZERO);
        sale.setNetTotalForTransaction(saleTotal);// TODO: add discount
        sale.setCatSaleStatus(catSaleStatus);

        // TODO: update stock

        // TODO: save here

        // TODO: create sale
        return sale;
    }
}
