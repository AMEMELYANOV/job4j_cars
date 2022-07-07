package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(User user) {
        return userRepository.add(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByUserEmail(email);
    }

    public User findUserById(Integer userId) {
        return userRepository.findUserByUserId(userId);
    }

    public User update(User user) {
        return userRepository.update(user);
    }
}
