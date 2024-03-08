package com.StudDept.services;

import com.StudDept.entity.Student;
import com.StudDept.repository.DeptRepository;
import com.StudDept.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DeptRepository deptRepository;

    public String create(Student student) {
        studentRepository.save(student);
        return "Department Is Created !";
    }

    public String update(Long id, Student department) {
        if (studentRepository.existsById(id)){
            studentRepository.save(department);
            return "Updated!";
        }
        return "Id Not Found!";
    }

    public Page<Student> getAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Student getById(Long id) {
        Optional<Student> department = studentRepository.findById(id);
        return department.orElse(null);
    }

    public Student delete(Long id, Student student) {
        if (studentRepository.existsById(id)){
            studentRepository.delete(student);
        }
        return null;
    }

    public Page<Student> getByDeptName(Pageable pageable, String deptName) {
        return studentRepository.findAllStudByDepName(pageable, deptName);
    }
}
