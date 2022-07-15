package ru.job4j.cars.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Имя аккаунта или пароль введены неправильно!";
        }
        if (logout != null) {
            errorMessage = "Вы вышли!";
        }
        model.addAttribute("errorMessage", errorMessage);

        log.info("Method {} run", "loginPage");
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, HttpServletRequest req) {
        User userFromDB = userService.findUserByEmail(user.getEmail());
        if (userFromDB != null && userFromDB.getPassword().equals(user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userFromDB);
            return "redirect:/ads";
        }

        log.info("Method {} run", "loginUser");
        return "redirect:/login?error=true";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpSession session) {
        session.invalidate();

        log.info("Method {} run", "logoutPage");
        return "redirect:/login?logout=true";
    }
}
