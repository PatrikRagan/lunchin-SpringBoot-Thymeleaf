package com.patres.controller;

import com.patres.model.User;
import com.patres.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value = "/users/{id}/delete", method = GET)
    public ModelAndView deleteUser(@PathVariable long id) {
        userRepository.delete(id);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users/new", method = GET)
    public String newUser() {
        return "users/new";
    }

    @RequestMapping(value = "/users/create", method = POST)
    public ModelAndView createUser(@RequestParam("firstName") String firstName) {
        userRepository.save(new User(firstName, "byREST"));
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users/update", method = POST)
    public ModelAndView updateUser(@RequestParam("user_id") long id,
                                   @RequestParam("firstName") String firstName) {
        User user = userRepository.findOne(id);
        user.setFirstName(firstName);
        userRepository.save(user);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editUser(@PathVariable long id, Model model){
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        return "users/edit";
    }
}
