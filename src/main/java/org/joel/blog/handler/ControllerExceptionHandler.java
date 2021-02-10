package org.joel.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义一个全局异常处理类，用于跳转到自定义的异常处理界面
 * 这个类可以防止程序直接崩溃
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)  // 表示该方法用于处理异常，括号内是异常的级别
    public ModelAndView exceptionHandel(HttpServletRequest request, Exception e) throws Exception {
        logger.warn("Request URL:{},Exception:{}", request.getRequestURL(), e.getMessage());  // 这里会让异常在控制台显示
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;  // 对异常判断，如果有状态码，是特定的异常类型，就直接抛出
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("error/error");  // 返回error页面
        return modelAndView;
    }
}
