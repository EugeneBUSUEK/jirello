package com.tinkoffworkshop.jirello.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @RequestMapping("/hello")
    public String greeting() {
        return "hello";
    }
}
