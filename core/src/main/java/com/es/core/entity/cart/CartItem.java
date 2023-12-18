package com.es.core.entity.cart;

import com.es.core.entity.ball.Ball;

public class CartItem {

    private Ball ball;
    private Long quantity;

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
