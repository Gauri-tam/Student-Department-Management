package com.StudDept.controller;

import com.StudDept.request.UserAuthenticationRequest;
import com.StudDept.request.UserRegistrationRequest;
import com.StudDept.response.UserAuthenticationResponse;
import com.StudDept.response.UserRegistrationResponse;
import com.StudDept.services.JwtAuthenticateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final JwtAuthenticateService jwtAuthenticateService;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest request){
        return ResponseEntity.ok(jwtAuthenticateService.registration(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserAuthenticationResponse> authentication(@RequestBody UserAuthenticationRequest request){
        return ResponseEntity.ok(jwtAuthenticateService.authentication(request));
    }

    @PostMapping("/refresh")
    public void  refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        jwtAuthenticateService.refreshToken(request, response);
    }
}
