package com.example.dossiermed.Repositories;

import com.example.dossiermed.Models.SqliUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SqliUserRepository extends JpaRepository<SqliUser, Integer> {
    Optional<SqliUser> findByEmail(String email);
    Boolean existsByEmail(String email);
    @Query("SELECT su FROM SqliUser su JOIN su.authorities r WHERE r.name = :roleName AND su.estPartie = false")
    List<SqliUser> findByRoleAndEstPartieFalse(@Param("roleName") String roleName);
}
