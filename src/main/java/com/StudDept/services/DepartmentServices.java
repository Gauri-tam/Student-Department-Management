package com.StudDept.services;

import com.StudDept.entity.Department;
import com.StudDept.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServices {

    private final DeptRepository deptRepository;

    public String create(Department department) {
        deptRepository.save(department);
        return "Department Is Created !";
    }

    public String update(Long id, Department department) {
        if (deptRepository.existsById(id)){
            deptRepository.save(department);
            return "Updated!";
        }
        return "Id Not Found!";
    }

    public Page<Department> getAll(Pageable pageable) {
        return deptRepository.findAll(pageable);
    }

    public Department getById(Long id) {
        Optional<Department> department = deptRepository.findById(id);
        return department.orElse(null);
    }

    public Department delete(Long id, Department department) {
        if (deptRepository.existsById(id)){
            deptRepository.delete(department);
        }
        return null;
    }

    public Page<Department> getByStudName(Pageable pageable, String studName) {
        return deptRepository.findByDeptByStudName(pageable, studName);
    }
}
