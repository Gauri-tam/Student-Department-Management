package com.StudDept.controller;

import com.StudDept.entity.Department;
import com.StudDept.entity.Student;
import com.StudDept.request.UserRegistrationRequest;
import com.StudDept.response.UserRegistrationResponse;
import com.StudDept.services.DepartmentServices;
import com.StudDept.services.PrincipleService;
import com.StudDept.services.StudentService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pri")
@RequiredArgsConstructor
public class PrincipalController {

    private final PrincipleService principleService;

    private final DepartmentServices departmentServices;

    // http://localhost:8080/api/v1/pri/register-hod
    @PostMapping("/register-hod")
    public ResponseEntity<UserRegistrationResponse> hodRegister(@Valid @RequestBody UserRegistrationRequest request, HttpServletRequest req) throws MessagingException {
        return ResponseEntity.ok(principleService.hodRegister(request, req));
    }

    @PostMapping("/register-tea")
    public ResponseEntity<UserRegistrationResponse> teaRegister(@Valid @RequestBody UserRegistrationRequest request, HttpServletRequest req) throws MessagingException {
        return ResponseEntity.ok(principleService.teacherRegister(request, req));
    }

    @PostMapping("/dept")
    public ResponseEntity<String> createDept(@RequestBody Department department){
        String status = departmentServices.create(department);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/dept/{id}")
    public ResponseEntity<String> updateDept(@PathVariable("id") long id,@RequestBody Department department){
        String status = departmentServices.update(id, department);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/get/dept")
    public ResponseEntity<Page<Department>> getAllDept(@RequestParam(value = "No.", defaultValue = "0", required = true) Integer pageNo,
                                                       @RequestParam(value = "Size", defaultValue = "5", required = true) Integer pageSize ){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        var status = departmentServices.getAll(pageable);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/dept")
    public ResponseEntity<Page<Department>> getBydept(@RequestParam(value = "No", defaultValue = "0", required = true) Integer pageNo,
                                                      @RequestParam(value = "Size", defaultValue = "5", required = true) Integer pageSize,
                                                      @RequestParam(value = "studName", defaultValue = "Gauri", required = false)String studName){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Department> status = departmentServices.getByStudName(pageable, studName);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/dept/{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") Long id){
        Department status = departmentServices.getById(id);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }


    @DeleteMapping("/dept/{id}")
    public ResponseEntity<Department> deleteDept(@PathVariable("id") Long id, Department department){
        Department status = departmentServices.delete(id, department);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
//-------------------------------------------------------------------------

    private final StudentService studentService;

    @PostMapping("/stud")
    public ResponseEntity<String> createStud(@RequestBody Student department){
        String status = studentService.create(department);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/stud/{id}")
    public ResponseEntity<String> updateStud(@PathVariable("id") long id,@RequestBody Student student){
        String status = studentService.update(id, student);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/get/stud")
    public ResponseEntity<Page<Student>> getAllStud(@RequestParam(value = "No", defaultValue = "0", required = true) Integer pageNo,
                                                    @RequestParam(value = "Size", defaultValue = "5", required = true) Integer pageSize ){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        var status = studentService.getAll(pageable);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<Page<Student>> getStudBy(@RequestParam(value = "No", defaultValue = "0", required = true) Integer pageNo,
                                                   @RequestParam(value = "Size", defaultValue = "5", required = true) Integer pageSize,
                                                   @RequestParam(value = "deptName", defaultValue = "Civil", required = true) String deptName){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Student> status = studentService.getByDeptName(pageable, deptName);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/stud/{id}")
    public ResponseEntity<Student> getStudById(@PathVariable("id") Long id){
        Student status = studentService.getById(id);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }


    @DeleteMapping("/stud/{id}")
    public ResponseEntity<Student> deleteStud(@PathVariable("id") Long id, Student student){
        Student status = studentService.delete(id, student);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
}
