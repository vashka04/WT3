package com.es.core.entity.cart.dto;

import java.math.BigDecimal;

public class CartAddDto {
    private String message;
    private boolean errorStatus;
    private long ballId;
    private long totalQuantity;
    private BigDecimal totalCost;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(boolean errorStatus) {
        this.errorStatus = errorStatus;
    }

    public long getBallId(Long ballId) {
        return this.ballId;
    }

    public void setBallId(long ballId) {
        this.ballId = ballId;
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
}
