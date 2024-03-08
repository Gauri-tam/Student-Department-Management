package com.StudDept.repository;

import com.StudDept.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "Select d.dept_id, d.dept_name, d.dept_code, s.stud_id, s.stud_name, s.stud_email, s.phone \n" +
            "from department d left join student s on s.dept_id = d.dept_id  \n" +
            "where d.dept_name like %:deptName%", nativeQuery = true)
    Page<Student> findAllStudByDepName(Pageable pageable, @Param("deptName") String deptName);
}
