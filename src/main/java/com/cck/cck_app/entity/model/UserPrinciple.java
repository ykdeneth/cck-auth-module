package com.cck.cck_app.entity.model;

import com.cck.cck_app.entity.Roles;
import com.cck.cck_app.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class UserPrinciple  implements UserDetails {

    private final User user;

    public UserPrinciple(User user) {
        this.user = user;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return user.getUserRoles()
//                .stream()
//                .map(userRole ->  new SimpleGrantedAuthority("ROLE_"+userRole.getRoles().getRole_name()))
//                .toList();
//    }
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getUserRoles()
            .stream()
            .flatMap(userRole -> {
                Roles role = userRole.getRoles();

                // 1. ROLE_ authority
                Stream<GrantedAuthority> roleAuth =
                        Stream.of(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));

                // 2. permission authorities from RolesPermissions
                Stream<GrantedAuthority> permAuth = role.getRoles().stream() // Set<RolesPermissions> roles;
                        .map(rp -> new SimpleGrantedAuthority(rp.getPermissions().getPermission()));

                return Stream.concat(roleAuth, permAuth);
            })
            .toList();
}


    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }
}
