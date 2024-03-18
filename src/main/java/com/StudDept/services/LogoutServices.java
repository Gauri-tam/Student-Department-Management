package com.StudDept.services;



import com.StudDept.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;

@Service
@RequiredArgsConstructor
public class LogoutServices implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @SneakyThrows
    @Override
    public void logout(@NotNull HttpServletRequest request,@NotNull HttpServletResponse response,@NotNull Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        String token;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        token = authHeader.substring(7);
        var userToken = tokenRepository.findUserByToken(token).orElseThrow();
        if (userToken != null){
            userToken.setRevoked(true);
            userToken.setExpired(true);
            tokenRepository.save(userToken);
            SecurityContextHolder.clearContext();
            PrintWriter writer = response.getWriter();
            writer.println("User Logout!");
        }
    }
}