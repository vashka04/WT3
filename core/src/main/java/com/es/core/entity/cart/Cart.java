package com.es.core.entity.cart;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
//@SessionScope
//@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class Cart {
    private List<CartItem> items = new ArrayList<CartItem>();
    private long totalQuantity;
    private BigDecimal totalCost = new BigDecimal(0);

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Optional<CartItem> findItemById(Long ballId) {
        return items.stream()
                .filter(item -> item.getBall().getId().equals(ballId))
                .findAny();
    }
}
