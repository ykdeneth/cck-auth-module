package com.cck.cck_app.service;

import com.cck.cck_app.entity.RefreshToken;
import com.cck.cck_app.entity.User;
import com.cck.cck_app.entity.model.UserPrinciple;
import com.cck.cck_app.repo.RefreshTokenRepository;
import com.cck.cck_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailServiceAuth implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Optional<User> user =  userRepo.findByUsername(username);
        User user =  userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username+" not found"));


        return new UserPrinciple(user);
    }

    public String storeRefreshToken(String rfToken, User u) {
//        Optional<User> user =  userRepo.findByUsername(username);
//        User user =  userRepo.findByUsername(u.getUsername()).orElseThrow(() -> new UsernameNotFoundException(" not found"));
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(String.valueOf(rfToken));
        refreshToken.setUser(u);
        refreshToken.setRevoked(false);
        refreshToken.setExpired(false);

        refreshTokenRepository.save(refreshToken);


        return "";
    }
}
