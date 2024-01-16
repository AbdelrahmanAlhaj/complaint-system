package com.complaint.config;

import com.complaint.util.LoginUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class CustomAuditAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(LoginUtil.getCurrentUsername());
    }
}
