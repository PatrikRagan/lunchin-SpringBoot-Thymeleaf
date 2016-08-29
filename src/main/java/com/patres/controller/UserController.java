package com.patres.controller;

import com.patres.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Patres on 29. 8. 2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = GET)
    public String listUsers(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        return "users/list";
    }

}
