package com.cck.cck_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Entity
@Table(name = "users_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id")
    private Roles roles;


    @Column(nullable = false)
    private String status;

    public UserRole() {
    }

    public UserRole(Long id, User user, Roles roles, String status) {
        this.id = id;
        this.user = user;
        this.roles = roles;
        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole other = (UserRole) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
