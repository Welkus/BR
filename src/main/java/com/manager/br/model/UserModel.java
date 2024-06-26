package com.manager.br.model;

import com.manager.br.util.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String userPassword;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "userModel")
   private List<CheckOutModel> review;
}
