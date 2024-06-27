package com.manager.br.service;

import com.manager.br.dto.UserDto;
import com.manager.br.model.UserModel;
import com.manager.br.repository.UserRepository;
import com.manager.br.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(UserDto userDto) {
        UserModel userModel = UserModel.builder()
                .username(userDto.getUsername())
                .userPassword(passwordEncoder.encode(userDto.getPass()))
                .email(userDto.getEmail())
                .role(Roles.USER)
                .build();

        userRepository.save(userModel);
    }

    public void createUser(UserDto userDto, Roles role) {
        UserModel userModel = UserModel.builder()
                .username(userDto.getUsername())
                .userPassword(passwordEncoder.encode(userDto.getPass()))
                .email(userDto.getEmail())
                .role(role)
                .build();
        userRepository.save(userModel);
    }

    public List<String> getUserRole(String username) {
        Optional<UserModel> userMod = userRepository.findByUsername(username);

        if (userMod.isPresent()) {
            UserModel user = userMod.get();
            return List.of(user.getRole().toString());
        }
        return Collections.singletonList("USER");
    }

    public void editUser(long userId, UserDto userDto) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());

            if (userDto.getPass() != null && !userDto.getPass().isEmpty()) {
                user.setUserPassword(passwordEncoder.encode(userDto.getPass()));
            }
            user.setRole(userDto.getRole());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
    }
}
