package com.es.core.entity.order;

public enum OrderStatus {
    NEW, DELIVERED, REJECTED;
    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "NEW";
            case DELIVERED:
                return "DELIVERED";
            case REJECTED:
                return "REJECTED";
            default:
                return "UNKNOWN";
        }
    }
    public static OrderStatus fromString(String status) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.toString().equalsIgnoreCase(status)) {
                return orderStatus;
            }
        }
        return null;
    }
}
