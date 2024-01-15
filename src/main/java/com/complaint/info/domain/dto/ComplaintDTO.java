package com.complaint.info.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO {

    @NotEmpty
    private String type;
    @NotEmpty
    private String status;
    @NotEmpty
    private String description;

    private List<CommentDTO> comments;

}
