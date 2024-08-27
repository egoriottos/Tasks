package org.example.crud.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.crud.commands.user.CreateUserCommand;
import org.example.crud.commands.user.UpdateUserCommand;
import org.example.crud.entity.User;
import org.example.crud.repository.UserRepository;
import org.example.crud.services.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(CreateUserCommand command) {
        User user = User.builder()
                .email(command.getEmail())
                .name(command.getName())
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserInfo(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    @Transactional
    public void updateUser(Long id, UpdateUserCommand command) {
        User user = getUserInfo(id);
        user.setEmail(command.getEmail());
        user.setName(command.getName());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
