package com.example.dossiermed.Controllers;

import com.example.dossiermed.Models.RendezVous;
import com.example.dossiermed.Services.CollabServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collab")
@CrossOrigin("*")
public class CollabController {
    @Autowired
    private CollabServices collabServices;
    @PostMapping("/{id}/rendezvous")
    public void addRendezVousForCollaborateur(@PathVariable int id, @RequestBody RendezVous rendezVous){
        collabServices.addRendezVousForCollaborateur(id, rendezVous);
    }
    @GetMapping("/{id}/rendezvous")
    public List<RendezVous> getAllRendezVousForCollaborateur(@PathVariable int id){
        return collabServices.getAllRendezVousForCollaborateur(id);
    }
    @DeleteMapping("/rendezVous/{rdvId}")
    public void removeRendezVousForCollaborateur(@PathVariable Long rdvId){
        collabServices.removeRendezVousForCollaborateur(rdvId);
    }
    @PutMapping("/rendezvous/{rdvId}")
    public void updateRendezVousForCollaborateur(@PathVariable Long rdvId, @RequestBody RendezVous rendezVous) {
        collabServices.updateRendezVousForCollaborateur(rdvId, rendezVous);
    }
    @GetMapping("/tempsDeTravail")
    public String gettempsDeTravail(){
        return collabServices.getTempsDeTravail();
    }
}
