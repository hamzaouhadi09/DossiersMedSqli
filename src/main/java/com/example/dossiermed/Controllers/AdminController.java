package com.example.dossiermed.Controllers;

import com.example.dossiermed.Models.RendezVous;
import com.example.dossiermed.Models.SqliUser;
import com.example.dossiermed.Models.tempsDeTravail;
import com.example.dossiermed.Services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AdminServices adminServices;

    @GetMapping("/confirmed-rendezvous")
    public ResponseEntity<List<RendezVous>> getConfirmedRendezVous() {
        List<RendezVous> rendezVousList = adminServices.getConfirmedRendezVous();
        return ResponseEntity.ok(rendezVousList);
    }

    @PostMapping("/add-medecin")
    public ResponseEntity<Void> addMedecin(@RequestParam String nom,
                                           @RequestParam String prenom,
                                           @RequestParam String email,
                                           @RequestParam String password) {
        adminServices.addMedecin(nom, prenom, email, password);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/set-est-partie-true")
    public ResponseEntity<Void> setEstPartieToTrue(@RequestParam String email) {
        adminServices.setEstPartieToTrue(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/role-user/est-partie-false")
    public ResponseEntity<List<SqliUser>> getUsersWithRoleUserAndEstPartieFalse() {
        List<SqliUser> users = adminServices.getUsersWithRoleUserAndEstPartieFalse();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/role-medecin/est-partie-false")
    public ResponseEntity<List<SqliUser>> getUsersWithRoleMedecinAndEstPartieFalse() {
        List<SqliUser> users = adminServices.getUsersWithRoleMedecinAndEstPartieFalse();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add-date-travail")
    public ResponseEntity<Void> addDateDeTravail(@RequestParam String jour,
                                                 @RequestParam String heureDebut,
                                                 @RequestParam String heureFin) {
        adminServices.addDateDeTravail(jour, heureDebut, heureFin);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/up-date-travail/{id}")
    public tempsDeTravail updateTempsDeTravail(@PathVariable int id, @RequestBody tempsDeTravail newTempsDeTravail) {
        return adminServices.updateTempsDeTravail(id, newTempsDeTravail);
    }

}
