package com.example.dossiermed.Controllers;

import com.example.dossiermed.DTO.LoginDTO;
import com.example.dossiermed.DTO.LoginResponseDTO;
import com.example.dossiermed.DTO.SqliUserDto;
import com.example.dossiermed.Repositories.SqliUserRepository;
import com.example.dossiermed.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private SqliUserRepository sqliUserRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> register(@RequestBody SqliUserDto sqliUserDto){
        if(sqliUserRepository.existsByEmail(sqliUserDto.getEmail())) {
            return new ResponseEntity<>("User already existe", HttpStatus.BAD_REQUEST);
        }
        authenticationService.registerUser(sqliUserDto.getNom(), sqliUserDto.getPrenom(), sqliUserDto.getEmail(), sqliUserDto.getPassword());
        return new ResponseEntity<>("User registred success!", HttpStatus.OK);
    }
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO response = authenticationService.loginUser(loginDTO);
        if (response.getUser() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
