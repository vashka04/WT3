package com.es.ballshop.web.controller.pages;

import com.es.core.entity.ball.BallNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BallNotFoundException.class)
    public ModelAndView handleException(BallNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("errorPages/notFoundBall");
        modelAndView.addObject("errorMessage", exception.getErrorMessage());
        return modelAndView;
    }
}
