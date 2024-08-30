package org.example.springdto.controller;

import lombok.RequiredArgsConstructor;
import org.example.springdto.dto.EmployeeProjection;
import org.example.springdto.entity.Department;
import org.example.springdto.entity.Employee;
import org.example.springdto.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("controller")
public class Controller {
    private final EmployeeService employeeService;

    @PostMapping("/create/employee")
    public ResponseEntity<Employee> create( @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save( employee));
    }

    @PostMapping("/create/department")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(employeeService.createDepartment(department));
    }

    @DeleteMapping("/delete/employee/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        employeeService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeProjection>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProjection> get(@PathVariable long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }
}
