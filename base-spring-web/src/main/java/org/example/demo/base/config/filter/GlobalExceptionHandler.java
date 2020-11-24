package org.example.demo.base.config.filter;


import org.example.demo.base.exception.CustomException;
import org.example.demo.base.exception.CustomExceptionType;
import org.example.demo.base.model.AjaxResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "org.example.demo")
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customerException(CustomException e) {
        return AjaxResponse.error(e);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public AjaxResponse customerException(Throwable t) {
        t.printStackTrace();
        return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR, t.getMessage());
    }
}