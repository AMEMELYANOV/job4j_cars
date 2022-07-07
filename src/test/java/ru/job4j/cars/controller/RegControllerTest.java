package ru.job4j.cars.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import static org.mockito.Mockito.*;

class RegControllerTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = User.of("user", "email",
                "password", "+79051111111");
    }

    @Test
    void whenRegSaveSuccess() {
        String repassword = "password";
        UserService userService = mock(UserService.class);

        RegController regController = new RegController(userService);
        String template = regController.regSave(user, repassword);

        verify(userService, times(1)).add(user);
        Assertions.assertThat(template).isEqualTo("redirect:/login");
    }

    @Test
    void whenRegSaveIfUserExist() {
        String repassword = "password";
        UserService userService = mock(UserService.class);
        doReturn(new User()).when(userService).findUserByEmail(user.getEmail());

        RegController regController = new RegController(userService);
        String template = regController.regSave(user, repassword);

        verify(userService, times(0)).add(user);
        Assertions.assertThat(template).isEqualTo("redirect:/reg?account=true");
    }

    @Test
    void whenRegSaveIfPasswordNotEqual() {
        String repassword = "pwd";
        UserService userService = mock(UserService.class);

        RegController regController = new RegController(userService);
        String template = regController.regSave(user, repassword);

        verify(userService, times(0)).add(user);
        Assertions.assertThat(template).isEqualTo("redirect:/reg?password=true");
    }

    @Test
    void whenRegPageSuccess() {
        String password = null;
        String account = null;
        String errorMessage = null;
        UserService userService = mock(UserService.class);
        Model model = mock(Model.class);

        RegController regController = new RegController(userService);
        String template = regController.regPage(password, account, model);

        verify(model).addAttribute("errorMessage", errorMessage);
        Assertions.assertThat(template).isEqualTo("reg");
    }

    @Test
    void whenRegPageIfPasswordParameterNotNull() {
        String password = "true";
        String account = null;
        String errorMessage = "Пароли должны совпадать!";
        UserService userService = mock(UserService.class);
        Model model = mock(Model.class);

        RegController regController = new RegController(userService);
        String template = regController.regPage(password, account, model);

        verify(model).addAttribute("errorMessage", errorMessage);
        Assertions.assertThat(template).isEqualTo("reg");
    }

    @Test
    void whenRegPageIfAccountParameterNotNull() {
        String password = null;
        String account = "true";
        String errorMessage = "Аккаунт уже существует!";
        UserService userService = mock(UserService.class);
        Model model = mock(Model.class);

        RegController regController = new RegController(userService);
        String template = regController.regPage(password, account, model);

        verify(model).addAttribute("errorMessage", errorMessage);
        Assertions.assertThat(template).isEqualTo("reg");
    }

}