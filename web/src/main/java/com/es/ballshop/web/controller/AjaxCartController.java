package com.es.ballshop.web.controller;

import com.es.core.entity.cart.dto.CartAddDto;
import com.es.core.entity.cart.dto.CartItemDto;
import com.es.core.service.CartService;
import com.es.core.entity.order.OutOfStockException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public CartAddDto addBall(@RequestBody @Valid CartItemDto cartItem,
                               BindingResult bindingResult) {
        CartAddDto message = new CartAddDto();
        if (!bindingResult.hasErrors()) {
            cartService.addBall(cartItem.getBallId(), cartItem.getQuantity());
            message.setMessage("Successfully added to cart");
            message.setErrorStatus(false);
        } else {
            message.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            message.setErrorStatus(true);
        }
        message.getBallId(cartItem.getBallId());
        message.setTotalCost(cartService.getTotalCost());
        message.setTotalQuantity(cartService.getTotalQuantity());
        return message;
    }


    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public CartAddDto numberFormatException() {
        CartAddDto message = new CartAddDto();
        message.setMessage("Quantity must be number");
        message.setErrorStatus(true);
        message.setTotalCost(cartService.getTotalCost());
        message.setTotalQuantity(cartService.getTotalQuantity());
        return message;
    }

    @ExceptionHandler(OutOfStockException.class)
    @ResponseBody
    public CartAddDto outOfStockException(OutOfStockException exception) {
        CartAddDto message = new CartAddDto();
        message.setMessage("Out of stock. " + exception.getMessage());
        message.setErrorStatus(true);
        message.setTotalCost(cartService.getTotalCost());
        message.setTotalQuantity(cartService.getTotalQuantity());
        return message;
    }
}
