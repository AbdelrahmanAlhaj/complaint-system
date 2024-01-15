package com.complaint.user.controller;


import com.complaint.common.BaseController;
import com.complaint.user.domain.dto.AuthenticationRequest;
import com.complaint.user.domain.dto.AuthenticationResponse;
import com.complaint.user.domain.dto.UserDTO;
import com.complaint.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/")
public class UserController extends BaseController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<Void> register(@Validated @RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return handleCreatedResponse();
    }

    @GetMapping("current-user")
    public ResponseEntity<UserDTO> fetchCurrentUser() {
        UserDTO userDTO = userService.fetchCurrentUser();
        return handleResponse(userDTO, HttpStatus.OK);
    }

    @GetMapping("logout")
    public void logout() {

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Validated @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
