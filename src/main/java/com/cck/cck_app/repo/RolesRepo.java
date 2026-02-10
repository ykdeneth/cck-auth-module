package com.cck.cck_app.repo;

import com.cck.cck_app.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Long> {
//    Optional<Roles> findByRole_name(String role_name);
    Optional<Roles> findByRoleName(String role_name);
}
