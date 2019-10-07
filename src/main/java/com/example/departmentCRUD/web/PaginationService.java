package com.example.departmentCRUD.web;

import com.example.departmentCRUD.Department;
import com.example.departmentCRUD.repository.PaginationDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class PaginationService {
    private PaginationDao paginationDao;
    @Autowired
    public PaginationService(PaginationDao paginationDao){
        this.paginationDao = paginationDao;
    }

    public Iterable<Department> findJsonDataByCondition(String orderBy, String direction, int page, int size) {
        Sort sort = null;
        if (direction.equals("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, orderBy);
        }
        if (direction.equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, orderBy);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Department> data = paginationDao.findAll(pageable);
        ArrayList<Department> list = new ArrayList<>();
        for (Iterator<Department> it = data.iterator(); it.hasNext(); ) {
            Department department = it.next();
            list.add(department);
        }
        return list;
    }
}