package com.StudDept.repository;

import com.StudDept.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeptRepository extends JpaRepository<Department, Long> {

    @Query(value = "select d.dept_id, d.dept_name, d.dept_code " +
            "from department d left join student s on d.dept_id = s.dept_id " +
            "where stud_name like %:studName%", nativeQuery = true)
    Page<Department> findByDeptByStudName(Pageable pageable, @Param("studName") String studName);
}
