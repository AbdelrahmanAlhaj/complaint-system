package com.complaint.role.repository;

import com.complaint.role.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findByUsers_UserId(Long userId);

    @Query("select r from RoleEntity r where r.name in ?1")
    List<RoleEntity> findByNameIn(Collection<String> names);


}
