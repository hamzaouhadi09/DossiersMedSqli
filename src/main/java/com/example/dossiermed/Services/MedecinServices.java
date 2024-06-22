package com.example.dossiermed.Services;

import com.example.dossiermed.Models.RendezVous;
import com.example.dossiermed.Repositories.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedecinServices {
    @Autowired
    private RendezVousRepository rendezVousRepository;
    public List<RendezVous> getConfirmedRendezVous() {
        return rendezVousRepository.findByStatus("pris");
    }
    @Transactional
    public void updateStatus(Long id, String status) {
        rendezVousRepository.updateStatus(id, status);
    }
}
