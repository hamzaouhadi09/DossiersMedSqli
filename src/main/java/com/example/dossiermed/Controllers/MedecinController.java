package com.example.dossiermed.Controllers;

import com.example.dossiermed.Models.DossierMedical;
import com.example.dossiermed.Models.RendezVous;
import com.example.dossiermed.Services.MedecinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medecin")
@CrossOrigin("*")
public class MedecinController {
    @Autowired
    private MedecinServices medecinServices;

    @GetMapping("/confirmed-rendezvous")
    public List<RendezVous> getConfirmedRendezVous() {
        return medecinServices.getConfirmedRendezVous();
    }

    @PutMapping("/update-rendezvous/{id}")
    @Transactional
    public void updateStatus(@PathVariable Long id, @RequestParam String status) {
        medecinServices.updateStatus(id, status);
    }

    @PostMapping("/save-dossier")
    public DossierMedical saveDossierMedical(@RequestBody DossierMedical dossierMedical) {
        return medecinServices.saveDossierMedical(dossierMedical);
    }

    @GetMapping("/dossier/{id}")
    public Optional<DossierMedical> getDossierMedical(@PathVariable int id) {
        return medecinServices.getDossierMedical(id);
    }

    @GetMapping("/dossiers")
    public List<DossierMedical> getDossiersByUserNomAndPrénoms(@RequestParam String nom, @RequestParam String prénoms) {
        return medecinServices.getDossiersByUserNomAndPrénoms(nom, prénoms);
    }
}
