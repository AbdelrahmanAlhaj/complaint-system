package com.complaint.info.domain.mapper;

import com.complaint.common.BaseMapper;
import com.complaint.common.IgnoreUnmappedMapperConfig;
import com.complaint.info.domain.dto.CommentDTO;
import com.complaint.info.domain.entity.CommentEntity;
import org.mapstruct.Mapper;


@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface CommentMapper extends BaseMapper<CommentEntity, CommentDTO> {

}
