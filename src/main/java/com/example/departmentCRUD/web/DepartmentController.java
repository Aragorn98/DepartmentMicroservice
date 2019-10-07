package com.example.departmentCRUD.web;


import com.example.departmentCRUD.Department;
import com.example.departmentCRUD.repository.DepartmentRepository;
import com.example.departmentCRUD.repository.PaginationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//end::baseClass[]
//tag::baseClass[]

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentRepository departmentRepository;
    private PaginationDao paginationDao;


    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository, PaginationDao paginationDao) {
        this.departmentRepository = departmentRepository;
        this.paginationDao = paginationDao;
    }

    @GetMapping("/ps")
    public Iterable<Department> getAllDepartments(@RequestParam String orderBy, @RequestParam String direction,
                                               @RequestParam int page, @RequestParam int size) {
        PaginationService paginationService = new PaginationService(paginationDao);
        return paginationService.findJsonDataByCondition(orderBy, direction, page, size);


    }

    @GetMapping("/psOrderBy")
    public Iterable<Department> getAllDepartments(@RequestParam String orderBy) {
        PaginationService paginationService = new PaginationService(paginationDao);
        return paginationService.findJsonDataByCondition(orderBy, "ASC",0,5);
    }

    @GetMapping
    public Iterable<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/find")
    public Iterable<Department> findDepartments(@RequestParam String searchText) {
        return departmentRepository.findDepartmentByName(searchText);
    }

//    @GetMapping("/user/{username}")
//    public UserDetails getUserByUsername(@PathVariable("username") String username) throws UsernameNotFoundException {
//        System.out.println("HomeController.getUserByUsername");
//        System.out.println("username = " + username);
//        System.out.println("userRepository = " + departmentRepository.findAll());
//        Teacher teacher = teacherRepo.findByUsername(username);
//        if (teacher != null) {
//            return teacher;
//        }
//        throw new UsernameNotFoundException(
//                "Teacher '" + username + "' not found");
//    }

//    @GetMapping("/userID/{id}")
//    public Department getUserById(@PathVariable("id") long id) throws UsernameNotFoundException {
//        System.out.println("HomeController.getUserByUsername");
//        System.out.println("id = " + id);
//        System.out.println("userRepository = " + departmentRepository.findAll());
//        Department editTimetable = departmentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        if (editTimetable != null) {
//            return editTimetable;
//        }
//        throw new UsernameNotFoundException(
//                "Teacher '" + id + "' not found");
//    }


    @PostMapping("/department/add")
    public Department saveDepartment(@RequestBody @Valid Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/department/update/{id}")
    public void updateDepartment(@PathVariable long id, @RequestBody Department department) {
        if (department.getId() != id) {
            throw new IllegalStateException("Given department's ID doesn't match the ID in the path.");
        }
        departmentRepository.save(department);
    }

    @DeleteMapping("/department/delete/{id}")
    public void deleteDepartment(@PathVariable long id) {
        departmentRepository.deleteById(id);
    }

}