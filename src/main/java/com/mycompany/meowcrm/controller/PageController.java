package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.util.ISessionUser;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class PageController {

    @Autowired
    private ISessionUser sessionUser;

    @RequestMapping({"/crm.htm", "crm", "index"})
    public String getIndex(Model model) {
        model.addAttribute("userName", sessionUser.getSessionUser().getName());
        return "index";
    }

    @RequestMapping({"/login/login.htm", "/login/login"})
    public String getLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("filed", false);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "login";
    }

    @RequestMapping({"/login/filed"})
    public String getLoginFiled(Model model) {
        model.addAttribute("filed", true);
        return "login";
    }

    @RequestMapping({"/menu.htm", "menu"})
    public String getMenu(Model model) {
        model.addAttribute("isAdmin", !SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().filter(r -> r.getAuthority().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty());
        return "menu";
    }

    @RequestMapping({"/admin.htm", "admin"})
    public String getAdmin(Model model) {
        model.addAttribute("userName", sessionUser.getSessionUser().getName());
        return "admin";
    }

}
