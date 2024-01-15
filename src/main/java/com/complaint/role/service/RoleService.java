package com.complaint.role.service;

import com.complaint.role.domain.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> fetchUserRole(Long userId);

    void createRoles(List<RoleDTO> roles);

    void assignUserRoles(Long userId, List<RoleDTO> roles);
}
