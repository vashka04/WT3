package com.es.ballshop.web.controller.pages;

import com.es.core.entity.cart.Cart;
import com.es.core.service.CartService;
import com.es.core.dao.BallDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {
    @Resource
    private BallDao ballDao;
    @Resource
    private CartService cartService;

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") Long ballId, Model model) {
        model.addAttribute("ball", ballDao.get(ballId).orElse(null));
        return "productPage";
    }

    @ModelAttribute("cart")
    public Cart cartOnPage() {
        return cartService.getCart();
    }
}
