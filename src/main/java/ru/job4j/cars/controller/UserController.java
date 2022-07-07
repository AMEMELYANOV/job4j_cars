package ru.job4j.cars.controller;

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
        return "redirect:/userEdit?userId=" + user.getId() + "&password=true";
    }
}
