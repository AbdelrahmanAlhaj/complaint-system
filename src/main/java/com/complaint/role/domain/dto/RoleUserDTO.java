package com.complaint.role.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUserDTO {

    private List<Long> userIds;
    private List<Long> roleIds;
}
