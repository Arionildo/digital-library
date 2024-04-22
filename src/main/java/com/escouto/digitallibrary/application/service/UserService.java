package com.escouto.digitallibrary.application.service;

import com.escouto.digitallibrary.presentation.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO saveUser(UserDTO userDTO);
    void deleteUser(Long id);
    boolean existsById(Long id);
}