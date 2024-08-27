package org.example.crud.services.interfaces;

import org.example.crud.commands.user.CreateUserCommand;
import org.example.crud.commands.user.UpdateUserCommand;
import org.example.crud.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceInterface {
    User createUser(CreateUserCommand command);
    List<User> getAllUsers();
    User getUserInfo(Long id);
    void updateUser(Long id, UpdateUserCommand command);
    void deleteUser(Long id);
    User findByEmail(String email);
}
