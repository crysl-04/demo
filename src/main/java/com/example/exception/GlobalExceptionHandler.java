package com.example.exception;

import com.example.dto.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //包含了 @ControllerAdvice 和 @ResponseBody。它的作用是使得整个应用程序的所有控制器（Controllers）都可以使用此全局异常处理器.
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) //当控制器中抛出 Exception 类型的异常时，Spring 将会调用此方法来处理异常。
    public ApiResponse handleException(Exception e) {
        return new ApiResponse("500",false,"Internal server error",null);
    }
}
