package ru.job4j.cars.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

class LoginControllerTest {

    private User user;
    private User userFromDB;

    @BeforeEach
    void setUp() {
        user = User.of("user", "email",
                "password", "+79051111111");
        userFromDB = User.of("user", "email",
                "pwd", "+79051111111");
    }

    @Test
    void whenRegLoginSuccess() {
        String error = null;
        String logout = null;
        String errorMessage = null;

        UserService userService = mock(UserService.class);
        Model model = mock(Model.class);

        LoginController loginController = new LoginController(userService);
        String template = loginController.loginPage(error, logout, model);

        verify(model).addAttribute("errorMessage", errorMessage);
        Assertions.assertThat(template).isEqualTo("login");
    }

    @Test
    void whenLoginPageIfPasswordParameterNotNull() {
        String error = "true";
        String logout = null;
        String errorMessage = "Имя аккаунта или пароль введены неправильно!";
        UserService userService = mock(UserService.class);
        Model model = mock(Model.class);

        LoginController loginController = new LoginController(userService);
        String template = loginController.loginPage(error, logout, model);

        verify(model).addAttribute("errorMessage", errorMessage);
        Assertions.assertThat(template).isEqualTo("login");
    }

    @Test
    void whenLoginPageIfAccountParameterNotNull() {
        String error = null;
        String logout = "true";
        String errorMessage = "Вы вышли!";
        UserService userService = mock(UserService.class);
        Model model = mock(Model.class);

        LoginController loginController = new LoginController(userService);
        String template = loginController.loginPage(error, logout, model);

        verify(model).addAttribute("errorMessage", errorMessage);
        Assertions.assertThat(template).isEqualTo("login");
    }

    @Test
    void whenLoginUserExist() {
        HttpSession session = mock(HttpSession.class);
        UserService userService = mock(UserService.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        doReturn(user).when(userService).findUserByEmail(user.getEmail());
        doReturn(session).when(request).getSession();

        LoginController loginController = new LoginController(userService);
        String template = loginController.loginUser(user, request);

        verify(session).setAttribute("user", user);
        Assertions.assertThat(template).isEqualTo("redirect:/ads");
    }

    @Test
    void whenLoginUserNotExist() {
        UserService userService = mock(UserService.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        doReturn(null).when(userService).findUserByEmail(user.getEmail());

        LoginController loginController = new LoginController(userService);
        String template = loginController.loginUser(user, request);

        Assertions.assertThat(template).isEqualTo("redirect:/login?error=true");
    }

    @Test
    void whenLoginUserPasswordNotEqual() {
        UserService userService = mock(UserService.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        doReturn(userFromDB).when(userService).findUserByEmail(user.getEmail());

        LoginController loginController = new LoginController(userService);
        String template = loginController.loginUser(user, request);

        Assertions.assertThat(template).isEqualTo("redirect:/login?error=true");
    }

    @Test
    void whenLogoutPage() {
        HttpSession session = mock(HttpSession.class);
        UserService userService = mock(UserService.class);

        LoginController loginController = new LoginController(userService);
        String template = loginController.logoutPage(session);

        Assertions.assertThat(template).isEqualTo("redirect:/login?logout=true");
    }

}