package com.complaint.user.domain.mapper;

import com.complaint.common.BaseMapper;
import com.complaint.common.IgnoreUnmappedMapperConfig;
import com.complaint.user.domain.dto.UserDTO;
import com.complaint.user.domain.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface UserMapper extends BaseMapper<UserEntity, UserDTO> {



}
