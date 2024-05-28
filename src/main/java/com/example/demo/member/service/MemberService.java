package com.example.demo.member.service;

import com.example.demo.member.record.JoinRecord;
import com.example.demo.member.entity.Member;


public interface MemberService {
    Long join(JoinRecord joinRecord);
    Member login(String userId, String password);
}
