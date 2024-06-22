package com.example.dossiermed.Repositories;

import com.example.dossiermed.Models.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical,Integer> {
    List<DossierMedical> findByUserNomAndUserPrenom(String nom, String prénoms);
}
