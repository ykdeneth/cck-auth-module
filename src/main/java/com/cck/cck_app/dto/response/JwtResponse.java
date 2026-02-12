package com.cck.cck_app.dto.response;

import lombok.Data;

@Data
public class JwtResponse {
    private String status;
    private String token;
    private String refreshToken;
    private String type = "Bearer";
}
