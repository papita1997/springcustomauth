package com.pawan.springcustomauth.services;

import com.pawan.springcustomauth.model.CustomUserDetails;
import com.pawan.springcustomauth.model.User;
import com.pawan.springcustomauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(name);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("user not present in db"));
        return optionalUser.map(CustomUserDetails::new).get();
    }
}
