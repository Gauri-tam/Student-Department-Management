package com.StudDept.repository;

import com.StudDept.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Department, Long> {
}
