package com.StudDept.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studId;

    @NotEmpty
    private String studName;

    @NotEmpty
    private String studEmail;

    @NotEmpty
    private String phone;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "student")
    private StudInfo studInfo;
}
