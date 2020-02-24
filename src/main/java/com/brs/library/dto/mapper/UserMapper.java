package com.brs.library.dto.mapper;

import com.brs.library.dto.UserDTO;
import com.brs.library.entity.User;

public class UserMapper {
    public static User mapToUser(UserDTO userDTO){
        return User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .phoneNumber(userDTO.getPhoneNumber())
                .username(userDTO.getUsername())
                .build();
    }
}
