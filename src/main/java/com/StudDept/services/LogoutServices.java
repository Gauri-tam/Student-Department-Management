package com.StudDept.services;

import com.StudDept.component.JwtAuthenticateFilter;
import com.StudDept.entity.Token;
import com.StudDept.repository.TokenRepository;
import com.StudDept.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;

@Service
@RequiredArgsConstructor
public class LogoutServices implements LogoutHandler {

    private final JwtHelperServices jwtHelperServices;

    private final TokenRepository tokenRepository;


    @SneakyThrows
    @Override
    public void logout(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Authentication authentication) {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token;
//        String userEmail;
        if (header == null || header.startsWith("Bearer ")){
            return;
        }
        token = header.substring(7);
        var userToken = tokenRepository.findUserByToken(token).orElseThrow();
//        userEmail = jwtHelperServices.extractUsername(token);
//        for sending the email to the User.
        if (userToken != null){
         userToken.setRevoked(true);
         userToken.setExpired(true);
        }
        tokenRepository.save(userToken);
        PrintWriter writer = response.getWriter();
        writer.println("User Logout Successfully!");
    }
}
