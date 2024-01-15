package com.complaint.info.service;

import com.complaint.info.domain.ComplaintStatus;
import com.complaint.info.domain.dto.CommentDTO;
import com.complaint.info.domain.dto.ComplaintDTO;
import com.complaint.info.domain.dto.ComplaintFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComplaintService {
    void createComplaint(ComplaintDTO complaintDTO);

    ComplaintDTO fetchComplaint(Long complaintId);

    void updateStatus(Long complaintId, ComplaintStatus complaintStatus);

    void addComment(Long complaintId, CommentDTO commentDTO);

    Page<ComplaintDTO> fetchComplaintList(ComplaintFilter complaintFilter, Pageable pageable);

}
