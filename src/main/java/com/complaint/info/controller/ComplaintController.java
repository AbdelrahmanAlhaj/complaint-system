package com.complaint.info.controller;


import com.complaint.common.BaseController;
import com.complaint.info.domain.dto.CommentDTO;
import com.complaint.info.domain.dto.ComplaintDTO;
import com.complaint.info.domain.dto.ComplaintFilter;
import com.complaint.info.domain.dto.ComplaintStatusDTO;
import com.complaint.info.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/complaint")
public class ComplaintController extends BaseController {
    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<Void> createComplaint(@RequestBody ComplaintDTO complaintDTO) {
        complaintService.createComplaint(complaintDTO);
        return handleCreatedResponse();
    }

    @GetMapping("/{complaintId}")
    private ResponseEntity<ComplaintDTO> fetchComplaint(@PathVariable("complaintId") Long complaintId) {
        ComplaintDTO complaintDTO = complaintService.fetchComplaint(complaintId);
        return handleResponse(complaintDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ComplaintDTO>> fetchComplaints(ComplaintFilter complaintFilter, Pageable pageable) {
        return handleResponse(complaintService.fetchComplaintList(complaintFilter, pageable));
    }

    @PostMapping("/{complaintId}/update-status")
    public ResponseEntity<Void> updateComplaintStatus(@PathVariable("complaintId") Long complaintId,
                                                      @RequestBody ComplaintStatusDTO complaintStatusDTO) {
        complaintService.updateStatus(complaintId, complaintStatusDTO.getStatus());
        return handleCreatedResponse();
    }

    @PostMapping("/{complaintId}/comments")
    public ResponseEntity<Void> addComments(@PathVariable("complaintId") Long complaintId,
                                            @RequestBody CommentDTO commentDTO) {
        complaintService.addComment(complaintId, commentDTO);
        return handleCreatedResponse();
    }

}
