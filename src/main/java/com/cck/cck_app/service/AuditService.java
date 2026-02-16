package com.cck.cck_app.service;

import com.cck.cck_app.entity.AuditLog;
import com.cck.cck_app.repo.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    public void saveAudit(AuditLog auditLog) {
        auditLogRepository.save(auditLog);
    }
}
