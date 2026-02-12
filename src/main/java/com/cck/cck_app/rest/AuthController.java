package com.cck.cck_app.rest;

import com.cck.cck_app.dto.requests.AuthLoginRequest;
import com.cck.cck_app.dto.requests.AuthRegisterRequest;
import com.cck.cck_app.dto.response.JwtResponse;
import com.cck.cck_app.dto.response.RegisterResponse;
import com.cck.cck_app.entity.User;
import com.cck.cck_app.repo.RefreshTokenRepository;
import com.cck.cck_app.service.UserDetailServiceAuth;
import com.cck.cck_app.service.register.AuthService;
import com.cck.cck_app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody AuthRegisterRequest request) {
        User u = authService.register(request);
        RegisterResponse r = new RegisterResponse();
        r.setUsername(u.getUsername());
        r.setStatus("Success");
        r.setMessage("User registered successfully");
        return ResponseEntity.ok(r);
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
        response.setStatus("Login-success");
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
        userDetailService.storeRefreshToken(refreshToken, userDetails);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }

        String token = authHeader.substring(7);

        var storedToken = refreshTokenRepository.findByToken(token);

        if (storedToken.isPresent()) {
            storedToken.get().setRevoked(true);
            storedToken.get().setExpired(true);
            refreshTokenRepository.save(storedToken.get());
        }

        return ResponseEntity.ok("Logged out successfully");
    }
}
