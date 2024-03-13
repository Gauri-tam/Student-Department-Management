package com.StudDept.controller;

import com.StudDept.request.UserAuthenticationRequest;
import com.StudDept.request.UserRegistrationRequest;
import com.StudDept.response.UserAuthenticationResponse;
import com.StudDept.response.UserRegistrationResponse;
import com.StudDept.services.JwtAuthenticateService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final JwtAuthenticateService jwtAuthenticateService;

    @PostMapping("/register-pri")
    public ResponseEntity<UserRegistrationResponse> register(@Valid @RequestBody UserRegistrationRequest request) throws MessagingException {
        return ResponseEntity.ok(jwtAuthenticateService.registration(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserAuthenticationResponse> authentication(@Valid @RequestBody UserAuthenticationRequest request) throws MessagingException {
        return ResponseEntity.ok(jwtAuthenticateService.authentication(request));
    }

    @PostMapping("/refresh")
    public void  refreshToken(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response) throws Exception {
        jwtAuthenticateService.refreshToken(request, response);
    }
}
