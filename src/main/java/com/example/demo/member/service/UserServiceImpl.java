package com.example.demo.member.service;

import com.example.demo.exception.ExceptionCode;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.member.entity.User;
import com.example.demo.member.record.JoinRecord;
import com.example.demo.member.record.LoginResponseRecord;
import com.example.demo.member.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.jwt.JwtProperties.HEADER_STRING;
import static com.example.demo.jwt.JwtProperties.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(JoinRecord joinRecord) {
        return userRepository.save(joinRecord.toEntity(passwordEncoder, userRepository)).getId();
    }

    @Transactional(readOnly = true)
    public LoginResponseRecord login(HttpServletResponse response, String userid, String password){
        User entity = userRepository.findByUserid(userid).orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        if(passwordEncoder.matches(password, entity.getPassword())){
            String jwtToken = TOKEN_PREFIX.concat(JwtUtils.createJwtToken1(entity));
            response.addHeader(HEADER_STRING,jwtToken);

            System.out.println("성공");
            return new LoginResponseRecord(entity, jwtToken);
        }


        else {
            System.out.println("실패");
            throw new BusinessLogicException(ExceptionCode.MEMBER_UNAUTHORIZED);
        }
    }
}
