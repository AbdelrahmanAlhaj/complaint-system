package com.complaint.user.service;

import com.complaint.user.domain.dto.AuthenticationRequest;
import com.complaint.user.domain.dto.AuthenticationResponse;
import com.complaint.user.domain.dto.UserDTO;
import com.complaint.user.domain.entity.UserEntity;

public interface UserService {
    void saveUser(UserDTO userDTO);

    UserDTO fetchCurrentUser();

    UserEntity fetchCurrentUserEntity();

    AuthenticationResponse authenticate(AuthenticationRequest request);

    UserEntity fetchUserByUserId(Long userId);
}
