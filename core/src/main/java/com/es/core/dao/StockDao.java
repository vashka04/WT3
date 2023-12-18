package com.es.core.dao;

public interface StockDao {
    Integer availableStock(Long ballId);
    void reserve(Long ballId, Long quantity);
}
