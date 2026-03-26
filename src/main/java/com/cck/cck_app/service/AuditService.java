package com.cck.cck_app.service;

import com.cck.cck_app.entity.AuditLog;
import com.cck.cck_app.entity.User;
import com.cck.cck_app.repo.AuditLogRepository;
import com.cck.cck_app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditLogRepository auditLogRepository;
    private final UserRepo userRepo;
    public void saveAudit(AuditLog auditLog) {
        auditLogRepository.save(auditLog);
    }
    public User getUser(String username) {
//        User user = userRepo.findByUsername(username);

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );

        return null;
    }
}
