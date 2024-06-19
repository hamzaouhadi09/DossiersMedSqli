package com.example.dossiermed.Services;

import com.example.dossiermed.Models.RendezVous;
import com.example.dossiermed.Models.Role;
import com.example.dossiermed.Models.SqliUser;
import com.example.dossiermed.Models.tempsDeTravail;
import com.example.dossiermed.Repositories.RendezVousRepository;
import com.example.dossiermed.Repositories.RoleRepository;
import com.example.dossiermed.Repositories.SqliUserRepository;
import com.example.dossiermed.Repositories.tempsDeTravailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminServices {
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private SqliUserRepository sqliUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private tempsDeTravailRepository tempsDeTravail;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<RendezVous> getConfirmedRendezVous() {
        return rendezVousRepository.findByStatus("pris");
    }
    public void addMedecin(String nom, String prenom, String email, String password) {
        SqliUser medecin = new SqliUser();
        medecin.setEmail(email);
        medecin.setPassword(passwordEncoder.encode((password)));
        medecin.setNom(nom);
        medecin.setPrenom(prenom);
        medecin.setEstPartie(false);
        Role roles = roleRepository.findByName("MEDECIN").get();
        Set<Role> authorities = new HashSet<>();
        medecin.setAuthorities(authorities);
        authorities.add(roles);
        sqliUserRepository.save(medecin);
    }
    public void setEstPartieToTrue(String email) {
        SqliUser user = sqliUserRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEstPartie(true);
        sqliUserRepository.save(user);
    }
    public List<SqliUser> getUsersWithRoleUserAndEstPartieFalse() {
        return sqliUserRepository.findByRoleAndEstPartieFalse("USER");
    }
    public List<SqliUser> getUsersWithRoleMedecinAndEstPartieFalse() {
        return sqliUserRepository.findByRoleAndEstPartieFalse("MEDECIN");
    }
    public void addDateDeTravail(String jour, String heureDebut, String heurDeFin) {
        tempsDeTravail travail = new tempsDeTravail();
        travail.setJour(jour);
        travail.setHeureDebut(heureDebut);
        travail.setHeurDeFin(heurDeFin);
        tempsDeTravail.save(travail);
    }

}
