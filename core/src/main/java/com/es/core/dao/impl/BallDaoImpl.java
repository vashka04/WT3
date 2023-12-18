package com.es.core.dao.impl;

import com.es.core.entity.ball.Ball;
import com.es.core.entity.ball.sort.SortField;
import com.es.core.entity.ball.sort.SortOrder;
import com.es.core.dao.BallDao;
import com.es.core.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class BallDaoImpl implements BallDao {
    private final SessionFactory sessionFactory;

    public BallDaoImpl() {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Optional<Ball> get(Long key) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Ball.class, key));
        }
    }

    @Override
    public List<Ball> findAll(int offset, int limit, SortField sortField, SortOrder sortOrder, String query) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Ball> criteriaQuery = criteriaBuilder.createQuery(Ball.class);
            Root<Ball> root = criteriaQuery.from(Ball.class);

            criteriaQuery.select(root);
            if (query != null && !query.isEmpty()) {
                criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + query.toLowerCase() + "%"));
            }
            if (sortField != null && sortOrder != null) {
                Order order = sortOrder == SortOrder.ASC ? criteriaBuilder.asc(root.get(sortField.name().toLowerCase()))
                        : criteriaBuilder.desc(root.get(sortField.name().toLowerCase()));
                criteriaQuery.orderBy(order);
            }

            Query<Ball> queryResult = session.createQuery(criteriaQuery);
            queryResult.setFirstResult(offset);
            queryResult.setMaxResults(limit);

            return queryResult.getResultList();
        }
    }

    @Override
    public Long numberByQuery(String query) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            Root<Ball> root = countQuery.from(Ball.class);

            countQuery.select(criteriaBuilder.count(root));
            if (query != null && !query.isEmpty()) {
                countQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + query.toLowerCase() + "%"));
            }

            return session.createQuery(countQuery).getSingleResult();
        }
    }
}
