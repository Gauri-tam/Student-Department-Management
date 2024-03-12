package com.StudDept.confoguration;

import com.StudDept.component.JwtAuthenticateFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.StudDept.enumarate.Permission.*;
import static com.StudDept.enumarate.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticateFilter jwtAuthenticateFilter;

    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/api/v1/con/**").permitAll()                                         // mail url to create end point.requestMatchers( "/api/v1/pri/**").hasRole(PRINCIPLE.name())
                                .requestMatchers(GET,"/api/v1/pri/**" ).hasAuthority(PRINCIPLE_READ.name())
                                .requestMatchers(POST,"/api/v1/pri/**" ).hasAuthority(PRINCIPLE_CREATE.name())
                                .requestMatchers(PUT,"/api/v1/pri/**" ).hasAuthority(PRINCIPLE_UPDATE.name())
                                .requestMatchers(DELETE,"/api/v1/pri/**" ).hasAuthority(PRINCIPLE_DELETE.name())
                                .requestMatchers("/api/v1/hod/**").hasAnyRole(PRINCIPLE.name(), HOD.name())
                                .requestMatchers(GET, "/api/v1/hod/**").hasAnyAuthority(PRINCIPLE_READ.name(), HOD_READ.name())
                                .requestMatchers(POST,"/api/v1/hod/**").hasAnyAuthority(PRINCIPLE_CREATE.name(), HOD_CREATE.name())
                                .requestMatchers(PUT,"/api/v1/hod/**").hasAnyAuthority(PRINCIPLE_UPDATE.name(), HOD_UPDATE.name())
                                .requestMatchers("/api/v1/hod/**").hasAnyRole(PRINCIPLE.name(), TEACHER.name())
                                .requestMatchers(GET, "/api/v1/tea/**").hasAnyAuthority(PRINCIPLE_READ.name(), TEACHER_READ.name())
                                .requestMatchers(POST,"/api/v1/tea/**").hasAnyAuthority(PRINCIPLE_CREATE.name(), TEACHER_CREATE.name())
                                .requestMatchers(PUT,"/api/v1/tea/**").hasAnyAuthority(PRINCIPLE_UPDATE.name(), TEACHER_UPDATE.name())
//                        .anyRequest().authenticated())                                                                          //it will access all url which is present in your project.
                )
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout->logout
                        .logoutSuccessUrl("/api/v1/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
        return http.build();
    }
}
