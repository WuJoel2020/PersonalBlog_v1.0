package org.joel.blog.web;

import org.joel.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id, @PathVariable String name) {
//        int i = 9 / 0;
//        String blog = null;
//        if(blog == null){
//            throw new NotFoundException("博客找不到");
//        }
        System.out.println("----------index------------");
        return "index";
    }

    @GetMapping("/")
    public String index1() {
        return "index";
    }
}
