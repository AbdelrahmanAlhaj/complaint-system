package com.complaint.util;

import com.complaint.role.domain.Role;
import com.complaint.role.domain.entity.RoleEntity;
import com.complaint.user.domain.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Objects;

public class LoginUtil {

    public static String getCurrentUsername() {
        Authentication authentication = fetchAuthenticationObject();
        if (authentication == null) {
            return null;
        }
        return ((UserEntity) authentication.getPrincipal()).getUsername();
    }

    public static Long getCurrentUserId() {
        Authentication authentication = fetchAuthenticationObject();
        if (authentication == null) {
            return null;
        }
        return ((UserEntity) authentication.getPrincipal()).getUserId();
    }

    public static boolean isCurrentUserHasRole(Role role) {
        return Objects.requireNonNull(fetchCurrentUserRoles())
                .parallelStream()
                .anyMatch(userEntity -> userEntity.getName().equals(role.name()));
    }

    private static List<RoleEntity> fetchCurrentUserRoles() {
        Authentication authentication = fetchAuthenticationObject();
        if (authentication == null) {
            return null;
        }
        return ((UserEntity) authentication.getPrincipal()).getRoles();
    }

    private static Authentication fetchAuthenticationObject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return authentication;
    }
}
