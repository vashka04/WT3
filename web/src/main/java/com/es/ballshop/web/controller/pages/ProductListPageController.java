package com.es.ballshop.web.controller.pages;

import com.es.core.entity.cart.Cart;
import com.es.core.service.CartService;
import com.es.core.entity.ball.sort.SortField;
import com.es.core.entity.ball.sort.SortOrder;
import com.es.core.entity.ball.Ball;
import com.es.core.dao.BallDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {
    @Resource
    private BallDao ballDao;
    @Resource
    private CartService cartService;

    private static final int BALLS_ON_PAGE = 10;

    @GetMapping
    public String showProductList(@RequestParam(name = "page", required = false) Integer pageNumber,
                                  @RequestParam(name = "sort", required = false) String sortField,
                                  @RequestParam(name = "order", required = false) String sortOrder,
                                  @RequestParam(name = "query", required = false) String query,
                                  Model model) {
        List<Ball> balls = ballDao.findAll(((pageNumber == null ? 1 : pageNumber) - 1) * BALLS_ON_PAGE, BALLS_ON_PAGE,
                SortField.getValue(sortField), SortOrder.getValue(sortOrder), query);
        model.addAttribute("balls", balls);
        Long number = ballDao.numberByQuery(query);
        model.addAttribute("numberOfBalls", number);
        model.addAttribute("numberOfPages", (number + BALLS_ON_PAGE - 1) / BALLS_ON_PAGE);
        return "productList";
    }

    @ModelAttribute("cart")
    public Cart cartOnPage() {
        return cartService.getCart();
    }
}
