package com.es.core.dao;

import com.es.core.entity.ball.Ball;
import com.es.core.entity.ball.sort.SortField;
import com.es.core.entity.ball.sort.SortOrder;

import java.util.List;
import java.util.Optional;

public interface BallDao {
    Optional<Ball> get(Long key);

    List<Ball> findAll(int offset, int limit, SortField sortField, SortOrder sortOrder, String query);

    Long numberByQuery(String query);
}
