package com.escouto.digitallibrary.application.mapper;

import com.escouto.digitallibrary.domain.entity.User;
import com.escouto.digitallibrary.presentation.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        return user;
    }
}
