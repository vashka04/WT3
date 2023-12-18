package com.es.core.entity.ball;

public class BallNotFoundException extends RuntimeException {
    public String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BallNotFoundException() {
        this.errorMessage = "Ball not found!";
    }
}
