package com.cck.cck_app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Roles {

    @Id
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "roles",  fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "roles",  fetch = FetchType.EAGER)
    private Set<RolesPermissions> roles;

    public Roles() {
    }

    public Roles(Long id, String roleName, String roleDesc, LocalDateTime createdAt, Set<UserRole> userRoles, Set<RolesPermissions> roles) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.createdAt = createdAt;
        this.userRoles = userRoles;
        this.roles = roles;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roles)) return false;
        Roles other = (Roles) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
