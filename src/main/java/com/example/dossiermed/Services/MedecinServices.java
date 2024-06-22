package com.example.dossiermed.Services;

import com.example.dossiermed.Models.DossierMedical;
import com.example.dossiermed.Models.RendezVous;
import com.example.dossiermed.Repositories.DossierMedicalRepository;
import com.example.dossiermed.Repositories.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinServices {
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;
    public List<RendezVous> getConfirmedRendezVous() {
        return rendezVousRepository.findByStatus("pris");
    }
    @Transactional
    public void updateStatus(Long id, String status) {
        rendezVousRepository.updateStatus(id, status);
    }

    public DossierMedical saveDossierMedical(DossierMedical dossierMedical) {
        return dossierMedicalRepository.save(dossierMedical);
    }
    public Optional<DossierMedical> getDossierMedical(int id) {
        return dossierMedicalRepository.findById(id);
    }
    public List<DossierMedical> getDossiersByUserNomAndPrénoms(String nom, String prénoms) {
        return dossierMedicalRepository.findByUserNomAndUserPrenom(nom, prénoms);
    }

}
