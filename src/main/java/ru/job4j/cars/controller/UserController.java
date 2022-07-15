package ru.job4j.cars.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

@Controller
public class UserController {

    static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userEdit{userId}")
    public String getEdit(@RequestParam(value = "userId") Integer userId, Model model,
                          @RequestParam(value = "password", required = false) String password) {
        String errorMessage = null;
        if (password != null) {
            errorMessage = "Неверно введен старый пароль!";
        }
        model.addAttribute("errorMessage", errorMessage);

        User user = userService.findUserById(userId);
        model.addAttribute("user", user);

        log.info("Method {} run", "getEdit");
        return "userEdit";
    }

    @PostMapping("/userEdit")
    public String userEdit(@ModelAttribute User user,
                           @RequestParam(value = "oldPassword") String oldPassword) {

        User userFromDB = userService.findUserById(user.getId());
        if (oldPassword != null && oldPassword.equals(userFromDB.getPassword())) {
            userService.update(user);
            return "redirect:/ads";
        }

        log.info("Method {} run", "userEdit");
        return "redirect:/userEdit?userId=" + user.getId() + "&password=true";
    }
}
