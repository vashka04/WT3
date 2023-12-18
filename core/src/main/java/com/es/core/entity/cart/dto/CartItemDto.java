package com.es.core.entity.cart.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class CartItemDto {
    private Long ballId;
    @NotNull(message = "Quantity was empty")
    @Min(value = 1, message = "Quantity must be more then 0")
    private Long quantity;

    public CartItemDto() {
    }

    public CartItemDto(Long ballId, Long quantity) {
        this.ballId = ballId;
        this.quantity = quantity;
    }

    public Long getBallId() {
        return ballId;
    }

    public void setBallId(Long ballId) {
        this.ballId = ballId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
