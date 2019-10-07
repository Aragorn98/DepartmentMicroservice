package com.example.departmentCRUD.repository;

import com.example.departmentCRUD.Department;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PaginationDao extends PagingAndSortingRepository<Department, Integer> {

}