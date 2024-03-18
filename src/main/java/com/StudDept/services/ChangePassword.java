package com.StudDept.services;

import com.StudDept.entity.User;
import com.StudDept.repository.UserRepository;
import com.StudDept.request.ChangePasswordRequest;
import com.StudDept.response.ChangePasswordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ChangePassword {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    private final EmailSenderService emailSenderService;

    public ChangePasswordResponse changePass(ChangePasswordRequest request, Principal connectedUser) throws Exception {

        var user = (User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (bCryptPasswordEncoder.matches(request.getCurrentPass(), user.getPassword())){
            if (request.getNewPass().equals(request.getConformPass())){

                user.setPassword(bCryptPasswordEncoder.encode(request.getConformPass()));
                userRepository.save(user);
              //  emailSenderService.sendEmailChangePassword(user.getEmail(), request.getNewPass());                       // it will send email to a user
                return ChangePasswordResponse.builder()
                        .msg("Your Password has been changed! Please Check Your email!")
                        .build();
            }else {
                return ChangePasswordResponse.builder()
                        .msg("Your Change Password and Conform Password Not Match")
                        .build();
            }
        }else {
            return ChangePasswordResponse.builder()
                    .msg("This Password is Not in Database")
                    .build();
        }
    }
}
