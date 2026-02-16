//package com.cck.cck_app.service;
//
//import com.cck.cck_app.repo.RolePermissionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//
//@Service
//public class PermissionService {
//
//    @Autowired
//    private RolePermissionRepository rolePermissionRepository;
//
//    public boolean hasAccess(Authentication authentication, String permission) {
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//        for (GrantedAuthority authority : authorities) {
//            System.out.println(authority.getAuthority());
//            String role = authority.getAuthority().replace("ROLE_", "");// ROLE_USER
//
//            if (rolePermissionRepository.existsByRoleAndPermission(role, permission)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//}
