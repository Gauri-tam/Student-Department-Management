package com.StudDept.services;

import com.StudDept.entity.Token;
import com.StudDept.entity.User;
import com.StudDept.enumarate.Role;
import com.StudDept.enumarate.TokenType;
import com.StudDept.repository.TokenRepository;
import com.StudDept.repository.UserRepository;
import com.StudDept.request.UserAuthenticationRequest;
import com.StudDept.request.UserRegistrationRequest;
import com.StudDept.response.UserAuthenticationResponse;
import com.StudDept.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtAuthenticateService {

    private final JwtHelperServices jwtHelperServices;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRegistrationResponse registration(UserRegistrationRequest  request){
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(Role.PRINCIPLE)
                .build();
        Optional<User> currentUser = userRepository.findByEmail(request.getEmail());
        if (currentUser.isPresent()){
            return UserRegistrationResponse.builder()
                    .Username(request.getFirstName()+" "+request.getLastName())
                    .email(request.getEmail())
                    .message("User Email is Present!")
                    .build();
        }
        userRepository.save(user);
        return UserRegistrationResponse.builder()
                .Username(request.getFirstName()+" "+request.getLastName())
                .email(request.getEmail())
                .message("Principle User Is Created!")
                .build();
    }

    public UserAuthenticationResponse authentication(UserAuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()));
        var user = userRepository.findByEmail(request.getUsername()).orElseThrow();
        var access = jwtHelperServices.generateAccessToken(user);
        var refresh = jwtHelperServices.generateRefreshToken(user);
        revokedToken(user);
        userToken(user, access);
        return UserAuthenticationResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .build();
    }

    private void revokedToken(User user){
        var storeUser = tokenRepository.findTokenByUser(user.getUserId());
        if (storeUser == null ){
            return;
        }
        storeUser.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);}
            );
        tokenRepository.saveAll(storeUser);
    }

    private void userToken(User user, String access) {
        var token = Token.builder()
                    .token(access)
                    .tokenType(TokenType.BEARER)
                    .expired(false)
                    .revoked(false)
                    .user(user)
                    .build();
        tokenRepository.save(token);
    }
}
