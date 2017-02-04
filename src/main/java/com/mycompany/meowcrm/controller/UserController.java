package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.model.User;
import com.mycompany.meowcrm.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public long addClient(@RequestBody User user) {
        return userService.addClient(user);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public long updateClient(@RequestBody User user) {
        return userService.updateClient(user);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delClient(@PathVariable long id) {
        userService.delClient(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getClient(@PathVariable long id) {
        return userService.getClient(id);
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List getAll() {
        return userService.getAll();
    }

}
