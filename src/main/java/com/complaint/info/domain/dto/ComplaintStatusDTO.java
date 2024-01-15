package com.complaint.info.domain.dto;


import com.complaint.info.domain.ComplaintStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintStatusDTO {

    private ComplaintStatus status;
}
