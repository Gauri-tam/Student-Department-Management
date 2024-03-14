package com.StudDept.controller;

import com.StudDept.request.ChangePasswordRequest;
import com.StudDept.response.ChangePasswordResponse;
import com.StudDept.services.ChangePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class ChangePassController {

    private final ChangePassword changePassword;

    // http://localhost:8080/api/v1/users/change-password

    @PatchMapping("/change-password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) throws Exception {
        return ResponseEntity.ok(changePassword.changePass(request, connectedUser));
    }
}
