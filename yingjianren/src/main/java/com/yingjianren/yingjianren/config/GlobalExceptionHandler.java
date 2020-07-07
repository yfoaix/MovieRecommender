package com.yingjianren.yingjianren.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// 作为一个控制层的切面处理
// 出现异常就统一定向到error.html
// @ControllerAdvice
// public class GlobalExceptionHandler {
//     @ExceptionHandler(Exception.class) // 所有的异常都是Exception子类
//     public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) { // 出现异常之后会跳转到此方法
//         ModelAndView mav = new ModelAndView("error"); // 设置跳转路径
//         mav.addObject("exception", e); // 将异常对象传递过去
//         mav.addObject("url", request.getRequestURL()); // 获得请求的路径
//         mav.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());//错误状态码
//         return mav;
//     }
// }