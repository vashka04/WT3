package com.es.core.service.impl;

import com.es.core.dao.BallDao;
import com.es.core.dao.StockDao;
import com.es.core.entity.cart.Cart;
import com.es.core.entity.cart.CartItem;
import com.es.core.entity.order.OutOfStockException;
import com.es.core.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HttpSessionCartService implements CartService {
    @Resource
    private HttpSession httpSession;
    @Resource
    private BallDao ballDao;
    @Resource
    private StockDao stockDao;
    private static final String CART_SESSION_ATTRIBUTE = "cart";

    @Override
    public Cart getCart() {
        Cart cart = (Cart) httpSession.getAttribute(CART_SESSION_ATTRIBUTE);
        if (cart == null) {
            cart = new Cart();
            httpSession.setAttribute(CART_SESSION_ATTRIBUTE, cart);
        }
        return cart;
    }

    @Override
    public void addBall(Long ballId, Long quantity) throws OutOfStockException {
        Cart cart = getCart();
        CartItem item = cart.findItemById(ballId).orElse(null);
        Long stock = stockDao.availableStock(ballId).longValue();
        if (item == null) {
            if (stock - quantity < 0)
                throw new OutOfStockException("Available " + stock, ballId);
            item = new CartItem();
            item.setBall(ballDao.get(ballId).orElse(null));
            item.setQuantity(quantity);
            cart.getItems().add(item);
        } else {
            if (stock - (quantity + item.getQuantity()) < 0)
                throw new OutOfStockException("Available " + (stock - item.getQuantity()), ballId);
            item.setQuantity(item.getQuantity() + quantity);
        }
        recalculateCart(cart);
    }

    @Override
    public void update(Long ballId, Long ballQuantity) throws OutOfStockException {
        Cart cart = getCart();
        Long stock = stockDao.availableStock(ballId).longValue();
        if (stock - ballQuantity < 0) {
            throw new OutOfStockException("Available " + stock, ballId);
        }
        cart.getItems().stream()
                .filter(item -> Objects.equals(item.getBall().getId(), ballId))
                .forEach(item -> item.setQuantity(ballQuantity));
        recalculateCart(cart);
    }

    @Override
    public void clear(){
        Cart cart = getCart();
        cart.getItems().clear();
        recalculateCart(cart);
    }
    @Override
    public void remove(Long ballId) {
        Cart cart = getCart();
        cart.getItems().removeIf(item -> ballId.equals(item.getBall().getId()));
        recalculateCart(cart);
    }

    @Override
    public long getTotalQuantity() {
        return getCart().getTotalQuantity();
    }

    @Override
    public BigDecimal getTotalCost() {
        return getCart().getTotalCost();
    }

    public void recalculateCart(Cart cart) {
        cart.setTotalQuantity(cart.getItems().stream()
                .map(CartItem::getQuantity)
                .collect(Collectors.summingLong(q -> q.longValue()))
        );

        cart.setTotalCost(cart.getItems().stream()
                .map(item -> {
                    if (item.getBall().getPrice() == null)
                        return BigDecimal.ZERO;
                    else return item.getBall().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }
}
