package com.StudDept.enumarate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    PRINCIPLE_CREATE("create"),
    PRINCIPLE_READ("read"),
    PRINCIPLE_DELETE("delete"),
    PRINCIPLE_UPDATE("update"),
    HOD_CREATE("create"),
    HOD_READ("read"),
    HOD_UPDATE("update"),
    TEACHER_CREATE("create"),
    TEACHER_READ("read"),
    TEACHER_UPDATE("update");

    @Getter
    private final String permission;

}
