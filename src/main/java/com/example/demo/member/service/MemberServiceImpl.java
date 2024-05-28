package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.record.JoinRecord;
import com.example.demo.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(JoinRecord joinRecord) {
        return memberRepository.save(joinRecord.toEntity(passwordEncoder, memberRepository)).getId();
    }

    @Transactional(readOnly = true)
    public Member login(String userid, String password){
        Member entity = memberRepository.findByUserid(userid).orElseThrow(()-> new EntityNotFoundException("Not Found Such User"));
        if(passwordEncoder.matches(password, entity.getPassword()))
            return entity;
        else return null;
    }
}
