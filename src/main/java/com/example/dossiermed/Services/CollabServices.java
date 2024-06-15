package com.example.dossiermed.Services;

import com.example.dossiermed.Models.RendezVous;
import com.example.dossiermed.Models.SqliUser;
import com.example.dossiermed.Repositories.RendezVousRepository;
import com.example.dossiermed.Repositories.SqliUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollabServices {

    @Autowired
    private SqliUserRepository sqliUserRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;

    public void addRendezVousForCollaborateur(int id, RendezVous rendezVous) {
        SqliUser collab =  sqliUserRepository.findById(id).orElse(null);
        if (collab != null) {
            rendezVous.setUser(collab);
            collab.getRendezVousList().add(rendezVous);
            sqliUserRepository.save(collab);
        }
    }
    public List<RendezVous> getAllRendezVousForCollaborateur(int id) {
        SqliUser collab = sqliUserRepository.findById(id).orElse(null);
        if (collab != null) {
            return collab.getRendezVousList();
        }
        return new ArrayList<>();
    }
    public void removeRendezVousForCollaborateur(Long rdvId){
        rendezVousRepository.deleteById(rdvId);
    }
    public void updateRendezVousForCollaborateur(Long rdvId, RendezVous rendezVous){
        rendezVousRepository.findById(rdvId).map(rdv -> {
            rdv.setType(rendezVous.getType());
            rdv.setHeur(rendezVous.getHeur());
            rdv.setMotif(rendezVous.getMotif());
            rdv.setStatus(rendezVous.getStatus());
            rdv.setJours(rendezVous.getJours());
            rendezVousRepository.save(rdv);
            return null;
        }).orElse(null);
    }
}
