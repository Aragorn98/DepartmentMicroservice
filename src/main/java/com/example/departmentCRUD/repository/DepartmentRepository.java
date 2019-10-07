package com.example.departmentCRUD.repository;

import com.example.departmentCRUD.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

    @Query(
            value = "SELECT * FROM department u WHERE u.name like CONCAT('%',?1,'%')",
            nativeQuery = true)
    ArrayList<Department> findDepartmentByName(String name);
}