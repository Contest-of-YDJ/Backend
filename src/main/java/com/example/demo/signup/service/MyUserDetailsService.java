package com.example.demo.signup.service;

import com.example.demo.signup.entity.MyUserDetails;
import com.example.demo.signup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println("username = "+ username);
        return new MyUserDetails(
                userRepository.findByUsername(username)
                        .orElseThrow(()-> new IllegalArgumentException("Not Found Such User"))
        );
    }

}
