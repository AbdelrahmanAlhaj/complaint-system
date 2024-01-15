package com.complaint.role.domain.entity;


import com.complaint.common.BaseEntity;
import com.complaint.user.domain.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long roleId;
    @Column(nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

}
