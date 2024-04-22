package com.escouto.digitallibrary.application.service.impl;

import com.escouto.digitallibrary.application.mapper.UserMapper;
import com.escouto.digitallibrary.application.service.UserService;
import com.escouto.digitallibrary.domain.entity.User;
import com.escouto.digitallibrary.domain.exception.UserNotFoundException;
import com.escouto.digitallibrary.infrastructure.persistence.UserRepository;
import com.escouto.digitallibrary.presentation.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        Iterable<User> usersIterable = userRepository.findAll();
        return StreamSupport.stream(usersIterable.spliterator(), false)
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}