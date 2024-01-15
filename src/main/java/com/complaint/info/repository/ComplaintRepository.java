package com.complaint.info.repository;

import com.complaint.info.domain.ComplaintStatus;
import com.complaint.info.domain.entity.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ComplaintRepository extends JpaRepository<ComplaintEntity, Long> {
    @Transactional
    @Modifying
    @Query("update ComplaintEntity c set c.status = ?1 where c.id = ?2")
    int updateStatus(ComplaintStatus status, Long id);



}
