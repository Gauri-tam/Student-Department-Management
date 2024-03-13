package com.StudDept.enumarate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.StudDept.enumarate.Permission.*;

@RequiredArgsConstructor
public enum Role {

    PRINCIPAL(
            Set.of(
                    PRINCIPAL_CREATE,
                    PRINCIPAL_READ,
                    PRINCIPAL_DELETE,
                    PRINCIPAL_UPDATE,
                    HOD_CREATE,
                    HOD_READ,
                    HOD_UPDATE
            )
    ),
    HOD(
            Set.of(
                    HOD_CREATE,
                    HOD_READ,
                    HOD_UPDATE
            )
    ),
    TEACHER(
            Set.of(
                    TEACHER_CREATE,
                    TEACHER_READ,
                    TEACHER_UPDATE
            )
    );

    @Getter
    private final Set<Permission> permission;

    public List<SimpleGrantedAuthority> getAuthority(){
        var authority = getPermission()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authority.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authority;
    }

}
