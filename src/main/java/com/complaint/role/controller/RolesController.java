package com.complaint.role.controller;


import com.complaint.common.BaseController;
import com.complaint.role.domain.dto.RoleDTO;
import com.complaint.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/role")
public class RolesController extends BaseController {
    private final RoleService roleService;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> createUserRole(
            @PathVariable("userId") Long userId,
            @RequestBody List<RoleDTO> roles) {
        log.info(String.format("fetch user %s roles", userId));
        roleService.assignUserRoles(userId, roles);
        return handleCreatedResponse();
    }

    @GetMapping("/get-by-role-id/{userId}")
    public ResponseEntity<List<RoleDTO>> fetchRole(
            @PathVariable("userId") Long userId) {
        log.info(String.format("fetch user %s roles", userId));
        return handleResponse(roleService.fetchUserRole(userId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createRoles(
            @RequestBody List<RoleDTO> roles) {
        log.info("create role");
        roleService.createRoles(roles);
        return handleCreatedResponse();
    }

}
