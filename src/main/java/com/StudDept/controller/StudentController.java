package com.StudDept.controller;

import com.StudDept.entity.Student;
import com.StudDept.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/con")
@RequiredArgsConstructor
public class StudentController {

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
