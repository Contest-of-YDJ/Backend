package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberDetails;
import com.example.demo.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println("username = "+ username);
        return new MemberDetails(
                getUsername(username)
                        .orElseThrow(()-> new EntityNotFoundException("Not Found Such User"))
        );
    }
    public Optional<Member> getUsername(String username){
        return memberRepository.findByUsername(username);
    }

}
