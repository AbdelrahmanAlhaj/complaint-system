package com.complaint.user.service.impl;

import com.complaint.config.JwtService;
import com.complaint.user.domain.dto.AuthenticationRequest;
import com.complaint.user.domain.dto.AuthenticationResponse;
import com.complaint.user.domain.dto.UserDTO;
import com.complaint.user.domain.entity.UserEntity;
import com.complaint.user.domain.mapper.UserMapper;
import com.complaint.user.repository.UserRepository;
import com.complaint.user.service.UserService;
import com.complaint.util.LoginUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.mapToEntity(userDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public UserDTO fetchCurrentUser() {
        String currentUsername = LoginUtil.getCurrentUsername();
        UserEntity userDetails = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with username %s Not found", currentUsername)));
        return userMapper.mapToDto(userDetails);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication =
                UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authentication);
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    @Override
    public UserEntity fetchUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(
                String.format("User with id %s Not found", userId)));
    }
}
