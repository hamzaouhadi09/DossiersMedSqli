package com.example.dossiermed.Services;

import com.example.dossiermed.Repositories.SqliUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SqliUserDetailsService implements UserDetailsService {
    @Autowired
    private SqliUserRepository sqliUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return sqliUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }
}
