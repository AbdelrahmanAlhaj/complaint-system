package com.complaint.role.domain.mapper;

import com.complaint.common.BaseMapper;
import com.complaint.common.IgnoreUnmappedMapperConfig;
import com.complaint.role.domain.dto.RoleDTO;
import com.complaint.role.domain.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface RoleMapper extends BaseMapper<RoleEntity, RoleDTO> {
}
