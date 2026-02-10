package com.cck.cck_app.dto;

import com.cck.cck_app.entity.Roles;
import com.cck.cck_app.entity.UserRole;
import lombok.Data;


import java.util.Set;
import java.util.UUID;

@Data
public class AuthRegisterRequest {

//    private UUID userId;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Set<String> roles;
    private  boolean status;

    public AuthRegisterRequest(String username, String password, String email, String mobile, Set<String> roles, boolean status) {
//        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.roles = roles;
        this.status = status;

    }
    public AuthRegisterRequest() {}

}
