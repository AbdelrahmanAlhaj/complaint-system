package com.complaint.role.service.impl;

import com.complaint.role.domain.dto.RoleDTO;
import com.complaint.role.domain.entity.RoleEntity;
import com.complaint.role.domain.mapper.RoleMapper;
import com.complaint.role.repository.RoleRepository;
import com.complaint.role.service.RoleService;
import com.complaint.user.domain.entity.UserEntity;
import com.complaint.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final UserService userService;

    @Override
    public List<RoleDTO> fetchUserRole(Long userId) {
        userId = 772L;
        List<RoleEntity> roles = roleRepository.findByUsers_UserId(userId);
        return roleMapper.mapListToDto(roles);
    }

    @Override
    public void createRoles(List<RoleDTO> roles) {
        List<RoleEntity> roleEntities = roleMapper.mapListToEntity(roles);
        roleRepository.saveAll(roleEntities);
    }

    @Override
    public void assignUserRoles(Long userId, List<RoleDTO> roles) {
        List<String> rolesName = roles.stream().map(RoleDTO::getName).toList();
        List<RoleEntity> roleEntities = roleRepository.findByNameIn(rolesName);
        UserEntity userEntity = userService.fetchUserByUserId(userId);
        roleEntities.forEach(roleEntity -> roleEntity.setUsers(List.of(userEntity)));

        roleRepository.saveAll(roleEntities);
    }
}
