package org.joel.blog.web;

import org.joel.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        /*
         * 1/0会产生异常，加上try-catch去捕获并抛出自定义异常类，在全局异常处理类就可以进行自定义的处理
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new NotFoundException("1/0");
        }
        */
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        String Blog = null;
        if (Blog == null) {  // 这里抛出了一个自定义的异常类
            throw new NotFoundException("博客不存在");
        }
        return "blog";
    }
}
