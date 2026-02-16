package com.cck.cck_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String className;

    private String methodName;

    private String ipAddress;

    private LocalDateTime timestamp;

    private Long executionTime;

    private String status; // SUCCESS / FAILED

    @Column(length = 2000)
    private String errorMessage;
}
