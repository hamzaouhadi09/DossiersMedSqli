package com.example.dossiermed.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class DossierMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String antecedentsProfessionnels;
    private String antecedentsHereditaire;
    private String taille;
    private String poids;
    private Date dateDeLexamen;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private SqliUser user;

    public DossierMedical() {
        this.dateDeLexamen = new Date();
    }
}
