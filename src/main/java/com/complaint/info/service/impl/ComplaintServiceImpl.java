package com.complaint.info.service.impl;

import com.complaint.info.domain.ComplaintStatus;
import com.complaint.info.domain.dto.CommentDTO;
import com.complaint.info.domain.dto.ComplaintDTO;
import com.complaint.info.domain.dto.ComplaintFilter;
import com.complaint.info.domain.entity.CommentEntity;
import com.complaint.info.domain.entity.ComplaintEntity;
import com.complaint.info.domain.mapper.CommentMapper;
import com.complaint.info.domain.mapper.ComplaintMapper;
import com.complaint.info.repository.ComplaintRepository;
import com.complaint.info.service.ComplaintService;
import com.complaint.role.domain.Role;
import com.complaint.user.service.UserService;
import com.complaint.util.LoginUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintMapper.dtoEntity complaintDTOMapper;
    private final ComplaintMapper.filterEntity complaintFilterMapper;
    private final CommentMapper commentMapper;
    private final UserService userService;

    @Override
    public void createComplaint(ComplaintDTO complaintDTO) {
        complaintDTO.setStatus(ComplaintStatus.MODIFY.name());
        ComplaintEntity complaintEntity = complaintDTOMapper.mapToEntity(complaintDTO);
        complaintEntity.setUser(userService.fetchCurrentUserEntity());
        complaintRepository.save(complaintEntity);
    }

    @Override
    public ComplaintDTO fetchComplaint(Long complaintId) {
        Optional<ComplaintEntity> complaintEntity;
        if (LoginUtil.isCurrentUserHasRole(Role.ADMIN)) {
            complaintEntity = complaintRepository.findById(complaintId);
        } else {
            complaintEntity = complaintRepository.findByIdAndUserId(complaintId, LoginUtil.getCurrentUserId());
        }
        return complaintDTOMapper.mapToDto(complaintEntity.orElseThrow(() ->
                new EntityNotFoundException(String.format("Complaint id [%s] not found", complaintId))));
    }

    @Override
    public void updateStatus(Long complaintId, ComplaintStatus complaintStatus) {
        //TODO check complaint id is allowed for this user
        complaintRepository.updateStatus(complaintStatus, complaintId);
    }

    @Override
    public void addComment(Long complaintId, CommentDTO commentDTO) {
        ComplaintEntity complaintEntity = complaintRepository.findById(complaintId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Complaint id [%s] not found", complaintId)));
        CommentEntity commentEntity = commentMapper.mapToEntity(commentDTO);

        List<CommentEntity> complaintComments = complaintEntity.getComments();
        complaintComments.add(commentEntity);

        complaintEntity.setComments(complaintComments);
        complaintRepository.save(complaintEntity);
    }

    @Override
    public Page<ComplaintDTO> fetchComplaintList(ComplaintFilter complaintFilter, Pageable pageable) {
        //TODO if it is user then add the user id as a mandatory filter
        ComplaintEntity complaintEntity = complaintFilterMapper.mapToEntity(complaintFilter);

        if (!LoginUtil.isCurrentUserHasRole(Role.ADMIN)) {
            complaintEntity.setUser(userService.fetchCurrentUserEntity());
        }

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase().withIgnoreNullValues();
        Example<ComplaintEntity> complaintExample = Example.of(complaintEntity, exampleMatcher);

        Page<ComplaintEntity> complaints = complaintRepository.findAll(complaintExample, pageable);
        return complaintDTOMapper.mapPageToDto(complaints, pageable);
    }

}
