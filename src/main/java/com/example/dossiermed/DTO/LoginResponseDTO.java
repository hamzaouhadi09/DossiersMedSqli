package com.example.dossiermed.DTO;

import com.example.dossiermed.Models.SqliUser;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private SqliUser user;
    private String jwt;

    public LoginResponseDTO(SqliUser sqliUser, String token) {
        this.user = sqliUser;
        this.jwt = token;
    }
}
