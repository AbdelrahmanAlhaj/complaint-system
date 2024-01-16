package com.complaint.info.domain.entity;

import com.complaint.common.BaseEntity;
import com.complaint.info.domain.ComplaintStatus;
import com.complaint.user.domain.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complaint")
public class ComplaintEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "type")
    private String type;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    @Column(name = "description", length = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "complaintEntity")
    private List<CommentEntity> comments;
}

