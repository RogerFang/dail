package com.dail.controller.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Roger on 2016/12/17.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AuthorizationException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ResponseBody
    public String handlAuhorizationException() {
        return "401";
    }
}
