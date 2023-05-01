package com.example.demo.signup.dto;

import com.example.demo.signup.entity.User;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.stream.Collectors;
import java.util.Set;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String username;
    private String userId;
    private String password;


}
