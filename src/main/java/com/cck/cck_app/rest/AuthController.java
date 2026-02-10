package com.cck.cck_app.rest;

import com.cck.cck_app.dto.AuthLoginRequest;
import com.cck.cck_app.dto.AuthRegisterRequest;
import com.cck.cck_app.dto.JwtResponse;
import com.cck.cck_app.service.UserDetailServiceAuth;
import com.cck.cck_app.service.register.AuthService;
import com.cck.cck_app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceAuth userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AuthRegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthLoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        JwtResponse response = new JwtResponse();
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }
}
