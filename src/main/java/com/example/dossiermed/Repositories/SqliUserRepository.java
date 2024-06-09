package com.example.dossiermed.Repositories;

import com.example.dossiermed.Models.SqliUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SqliUserRepository extends JpaRepository<SqliUser, Integer> {
    Optional<SqliUser> findByEmail(String email);
    Boolean existsByEmail(String email);
}
