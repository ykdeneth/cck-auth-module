package com.cck.cck_app.service.register;

import com.cck.cck_app.dto.AuthRegisterRequest;
import com.cck.cck_app.entity.Roles;
import com.cck.cck_app.entity.User;
import com.cck.cck_app.entity.UserRole;
import com.cck.cck_app.repo.RolesRepo;
import com.cck.cck_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthService {


    private final UserRepo userRepository;
    private final RolesRepo rolesRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepo authRepository, RolesRepo rolesRepo, PasswordEncoder passwordEncoder) {
        this.userRepository = authRepository;
        this.rolesRepo = rolesRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(AuthRegisterRequest request) {

        User authUser = new User();
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if(request.getRoles()==null || request.getRoles().isEmpty()) {
            throw new RuntimeException("Roles cannot be empty");
//            authUser.setUserRoles(new);
        }


        authUser.setUser_id(UUID.randomUUID());
        authUser.setUsername(request.getUsername());
        authUser.setPassword(passwordEncoder.encode(request.getPassword()));

//        Set<String> roleNames = request.getRoles();
//        authUser.setUserRoles(request.getRoles());
        authUser.setStatus(true);
        authUser.setMobile(request.getMobile());
        authUser.setCreatedAt(LocalDateTime.now());
        Set<UserRole> userRoles = new HashSet<>();
        for (var roleSet : request.getRoles()) {
            Roles roleEntity = rolesRepo.findByRoleName(roleSet != null ? roleSet : "USER")
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleSet));
            UserRole userRole = new UserRole();
            userRole.setUser(authUser);
            userRole.setRoles(roleEntity);
            userRole.setStatus("ACTIVE");
            userRoles.add(userRole);
        }
        authUser.setUserRoles(userRoles);
        // Save credentials
      return userRepository.save(authUser);

}
    private void validateMobile(String mobile) {

        if (mobile == null || mobile.isBlank()) {
            throw new RuntimeException("Mobile number is required");
        }

        if (!mobile.matches("^[0-9]{10}$")) {
            throw new RuntimeException("Mobile number must contain exactly 10 digits");
        }
    }

}
