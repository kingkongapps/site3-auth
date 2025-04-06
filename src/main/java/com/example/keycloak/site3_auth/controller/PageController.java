package com.example.keycloak.site3_auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(value = "/")
    public String goHome() {
        System.out.println("goHome()...");
        return "/home";
    }

    @GetMapping(value = "/admin/admin-test1")
    public String goAdminTest1() {
        System.out.println("goAdminTest1()...");
        return "/admin/admin-test1";
    }

    @GetMapping(value = "/admin/admin-test2")
    public String goAdminTest2() {
        System.out.println("goAdminTest2()...");
        return "/admin/admin-test2";
    }

    @GetMapping(value = "/user/user-test1")
    public String goUserTest1() {
        System.out.println("goUserTest1()...");
        return "/user/user-test1";
    }

    @GetMapping(value = "/user/user-test2")
    public String goUserTest2() {
        System.out.println("goUserTest2()...");
        return "/user/user-test2";
    }

    @GetMapping(value = "/login")
    public String goLogin() {
        System.out.println("goLogin()...");
        return "/login/login";
    }

}
