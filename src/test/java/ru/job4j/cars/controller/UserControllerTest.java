package ru.job4j.cars.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import static org.mockito.Mockito.*;

class UserControllerTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = User.of("user", "email",
                "password", "+79051111111");
    }

    @Test
    void whenGetEditPageSuccess() {
        String errorMessage = null;
        String password = null;
        int id = 1;
        Model model = mock(Model.class);
        UserService userService = mock(UserService.class);
        doReturn(user).when(userService).findUserById(id);

        UserController userController = new UserController(userService);
        String template = userController.getEdit(id, model, password);

        verify(model).addAttribute("errorMessage", errorMessage);
        verify(model).addAttribute("user", user);
        Assertions.assertThat(template).isEqualTo("userEdit");
    }

    @Test
    void whenGetEditPageFail() {
        String errorMessage = "Неверно введен старый пароль!";
        String password = "true";
        int id = 1;
        Model model = mock(Model.class);
        UserService userService = mock(UserService.class);
        doReturn(user).when(userService).findUserById(id);

        UserController userController = new UserController(userService);
        String template = userController.getEdit(id, model, password);

        verify(model).addAttribute("errorMessage", errorMessage);
        verify(model).addAttribute("user", user);
        Assertions.assertThat(template).isEqualTo("userEdit");
    }

    @Test
    void whenUserEditSuccess() {
        String oldPassword = "password";
        UserService userService = mock(UserService.class);
        doReturn(user).when(userService).findUserById(user.getId());

        UserController userController = new UserController(userService);
        String template = userController.userEdit(user, oldPassword);

        verify(userService, times(1)).update(user);
        Assertions.assertThat(template).isEqualTo("redirect:/ads");
    }

    @Test
    void whenUserEditFail() {
        String oldPassword = "pwd";
        UserService userService = mock(UserService.class);
        doReturn(user).when(userService).findUserById(user.getId());

        UserController userController = new UserController(userService);
        String template = userController.userEdit(user, oldPassword);

        verify(userService, times(0)).update(user);
        Assertions.assertThat(template)
                .isEqualTo("redirect:/userEdit?userId=" + user.getId() + "&password=true");
    }
}