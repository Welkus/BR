package com.manager.br.dto;

import com.manager.br.util.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String username;
    private String email;
    private String pass;
    private String confirmPass;
    private Roles role;
}
