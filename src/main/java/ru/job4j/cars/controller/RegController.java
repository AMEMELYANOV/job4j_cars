package ru.job4j.cars.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/reg")
public class RegController {

    private final UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String regSave(@Valid @ModelAttribute User user, Errors errors,
                          @RequestParam String repassword) {
        if (errors.hasErrors()) {
            return "reg";
        }
        User userFromDB = userService.findUserByEmail(user.getEmail());
        if (userFromDB != null) {
            return "redirect:/reg?account=true";
        }
        if (!user.getPassword().equals(repassword)) {
            return "redirect:/reg?password=true";
        }
        user.setActive(true);
        userService.add(user);

        log.info("Method {} run", "regSave");
        return "redirect:/login";
    }

    @GetMapping
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

    @ModelAttribute
    public User user() {
        return new User();
    }
}