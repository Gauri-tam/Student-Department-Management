package com.StudDept.controller;

import com.StudDept.entity.Department;
import com.StudDept.services.DepartmentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/con")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentServices departmentServices;

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
    public ResponseEntity<Page<Department>> getBydept(@RequestParam(value = "No.", defaultValue = "0", required = true) Integer pageNo,
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
}
