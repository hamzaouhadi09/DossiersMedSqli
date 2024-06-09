package com.example.dossiermed.Services;

import com.example.dossiermed.DTO.LoginDTO;
import com.example.dossiermed.DTO.LoginResponseDTO;
import com.example.dossiermed.Models.Role;
import com.example.dossiermed.Models.SqliUser;
import com.example.dossiermed.Repositories.RoleRepository;
import com.example.dossiermed.Repositories.SqliUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SqliUserRepository sqliUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void registerUser(String nom, String prenom, String email, String password) {
        SqliUser user = new SqliUser();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode((password)));
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setEstPartie(false);
        Role roles = roleRepository.findByName("USER").get();
        Set<Role> authorities = new HashSet<>();
        user.setAuthorities(authorities);
        authorities.add(roles);
        sqliUserRepository.save(user);
    }
    public LoginResponseDTO loginUser(LoginDTO loginDTO) {
        try {
            // Authenticate user
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );

            // Generate JWT token
            String token = tokenService.generateJwt(auth);

            // Find user by email and ensure they exist
            Optional<SqliUser> userOptional = sqliUserRepository.findByEmail(loginDTO.getEmail());
            if (userOptional.isPresent()) {
                return new LoginResponseDTO(userOptional.get(), token);
            } else {
                // Return an appropriate response if user is not found
                return new LoginResponseDTO(null, "");
            }

        } catch (AuthenticationException e) {
            // Handle authentication failure
            return new LoginResponseDTO(null, "");
        } catch (Exception e) {
            // Handle other potential exceptions
            return new LoginResponseDTO(null, "");
        }
    }
}
