package com.jmSpringBoot.crud.controller.userControllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class MainUserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());
        boolean hasAdmin;
        if (roles.contains("ADMIN")) {
            hasAdmin=true;
        } else {
            hasAdmin=false;
        }
        model.addAttribute("hasAdmin", hasAdmin);
        model.addAttribute("userName", name);
        return "user";
    }
}
