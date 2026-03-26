package com.cck.cck_app.repo;

import com.cck.cck_app.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
//    User findByUsername(String username);
    boolean existsByUsername(String username);
    @EntityGraph(attributePaths = {
            "userRoles",
            "userRoles.roles",
            "userRoles.roles.roles",
            "userRoles.roles.roles.permissions"})
    Optional<User> findByUsername(String username);
}
