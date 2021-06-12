package senchenko.interview.services;

import senchenko.interview.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    List<User> findAllUsers();

    Optional<User> findUserById(Long id);

    void deleteUserById(Long id);
}
