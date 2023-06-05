package com.example.demo.user.service;

import com.example.demo.user.entity.MyUserDetails;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println("username = "+ username);
        return new MyUserDetails(
                getUsername(username)
                        .orElseThrow(()-> new EntityNotFoundException("Not Found Such User"))
        );
    }
    public Optional<User> getUsername(String username){
        return userRepository.findByUsername(username);
    }

}
