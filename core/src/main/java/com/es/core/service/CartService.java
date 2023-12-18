package com.es.core.service;

import com.es.core.entity.cart.Cart;
import com.es.core.entity.ball.Ball;
import com.es.core.entity.order.OutOfStockException;

import java.math.BigDecimal;

public interface CartService {

    Cart getCart();

    void addBall(Long ballId, Long quantity) throws OutOfStockException;

    void update(Long ballId, Long ballQuantity);
    void clear();
    void remove(Long ballId);
    long getTotalQuantity();

    BigDecimal getTotalCost();
}
