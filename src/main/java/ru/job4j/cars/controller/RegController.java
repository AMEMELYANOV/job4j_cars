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
public class RegController {

    static final Logger log = LoggerFactory.getLogger(RegController.class);
    private final UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, @RequestParam String repassword) {
        User userFromDB = userService.findUserByEmail(user.getEmail());
        if (userFromDB != null) {
            return "redirect:/reg?account=true";
        }
        if (!user.getPassword().equals(repassword)) {
            return "redirect:/reg?password=true";
        }
        userService.add(user);

        log.info("Method {} run", "regSave");
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "password", required = false) String password,
                          @RequestParam(value = "account", required = false) String account,
                          Model model) {
        String errorMessage = null;
        if (password != null) {
            errorMessage = "Пароли должны совпадать!";
        }
        if (account != null) {
            errorMessage = "Аккаунт уже существует!";
        }
        model.addAttribute("errorMessage", errorMessage);

        log.info("Method {} run", "regPage");
        return "reg";
    }
}