package com.cck.cck_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles_permissions")
public class RolesPermissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Roles roles;

    @ManyToOne
    @JoinColumn(name = "permissions_id")
    private Permissions permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RolesPermissions)) return false;
        RolesPermissions other = (RolesPermissions) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
//    @Column(nullable = false)
//    private String permission_name;

}
