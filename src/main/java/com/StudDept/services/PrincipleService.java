package com.StudDept.services;

import com.StudDept.entity.User;
import com.StudDept.enumarate.Role;
import com.StudDept.repository.UserRepository;
import com.StudDept.request.UserRegistrationRequest;
import com.StudDept.response.UserRegistrationResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipleService {

    @Value("${security.jwt.username}")
    private String username;

    @Value("${security.jwt.password}")
    private String password;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailSenderService emailSenderService;

    public UserRegistrationResponse hodRegister(UserRegistrationRequest request, HttpServletRequest req) throws MessagingException {

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if ( header != null && header.startsWith("Basic ")){
            String pair  = new String(Base64.decodeBase64(header.substring(6)));
            String userName = pair.split(":")[0];
            String passWord = pair.split(":")[1];
            if (userName.equals(username ) && passWord.equals(password)){
                var user = User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(bCryptPasswordEncoder.encode(request.getPassword()))
                        .phone(request.getPhone())
                        .role(Role.HOD)
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
                emailSenderService.sentEmailToHOD(request.getEmail());
                return UserRegistrationResponse.builder()
                        .Username(request.getFirstName()+" "+request.getLastName())
                        .email(request.getEmail())
                        .message("Principle User Is Created!")
                        .build();
            }else {
                return UserRegistrationResponse.builder()
                        .Username(request.getFirstName()+" "+request.getLastName())
                        .email(request.getEmail())
                        .message("Bad Credential!")
                        .build();
            }
        }else{
            return UserRegistrationResponse.builder()
                    .Username(request.getFirstName()+" "+request.getLastName())
                    .email(request.getEmail())
                    .message("Authentication Header not should be Null!")
                    .build();
        }
    }

    public UserRegistrationResponse teacherRegister(UserRegistrationRequest request, HttpServletRequest req) throws MessagingException {
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if ( header != null && header.startsWith("Basic ")){
            String pair  = new String(Base64.decodeBase64(header.substring(6)));
            String userName = pair.split(":")[0];
            String passWord = pair.split(":")[1];
            if (userName.equals(username ) && passWord.equals(password)){
                var user = User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(bCryptPasswordEncoder.encode(request.getPassword()))
                        .phone(request.getPhone())
                        .role(Role.TEACHER)
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
                emailSenderService.sentEmailToTea(request.getEmail());
                return UserRegistrationResponse.builder()
                        .Username(request.getFirstName()+" "+request.getLastName())
                        .email(request.getEmail())
                        .message("Principle User Is Created!")
                        .build();
            }else {
                return UserRegistrationResponse.builder()
                        .Username(request.getFirstName()+" "+request.getLastName())
                        .email(request.getEmail())
                        .message("Bad Credential!")
                        .build();
            }
        }else{
            return UserRegistrationResponse.builder()
                    .Username(request.getFirstName()+" "+request.getLastName())
                    .email(request.getEmail())
                    .message("Authentication Header not should be Null!")
                    .build();
        }
    }
}
