package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;

import java.util.List;

public interface UserRep {

 User findUserByUserEmail(String username);

 User add(User user);

 User update(User user);

 User findUserByUserId(Integer userId);

 List<User> getAllUsers();
}
