package com.manager.br.service;

import com.manager.br.dto.UserDto;
import com.manager.br.model.UserModel;
import com.manager.br.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogInService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public boolean isPassValid(String userName, String pass){
        Optional<UserModel> userMod = userRepository.findByUsername(userName);

        if(userMod.isPresent()){
            UserModel user = userMod.get();

            return passwordEncoder.matches(pass, user.getUserPassword());
        }
        return false;
    }

    public boolean isPassStrong(String pass){
        return pass.matches("^(?=.*\\d).{4,}$");
    }
}
