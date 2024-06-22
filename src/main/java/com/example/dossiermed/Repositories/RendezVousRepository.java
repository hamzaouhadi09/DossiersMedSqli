package com.example.dossiermed.Repositories;

import com.example.dossiermed.Models.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByStatus(String status);
    @Modifying
    @Transactional
    @Query("UPDATE RendezVous r SET r.status = :status WHERE r.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") String status);
}
