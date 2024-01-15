package com.complaint.info.domain.mapper;

import com.complaint.common.BaseMapper;
import com.complaint.common.IgnoreUnmappedMapperConfig;
import com.complaint.info.domain.dto.ComplaintDTO;
import com.complaint.info.domain.dto.ComplaintFilter;
import com.complaint.info.domain.entity.ComplaintEntity;
import org.mapstruct.Mapper;


public interface ComplaintMapper {

    @Mapper(config = IgnoreUnmappedMapperConfig.class)
    interface dtoEntity extends BaseMapper<ComplaintEntity, ComplaintDTO> {

    }

    @Mapper(config = IgnoreUnmappedMapperConfig.class)
    interface filterEntity extends BaseMapper<ComplaintEntity, ComplaintFilter> {

    }
}
