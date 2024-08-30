package org.example.springdto.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.springdto.dto.EmployeeProjection;
import org.example.springdto.entity.Department;
import org.example.springdto.entity.Employee;
import org.example.springdto.repository.DepartmentRepository;
import org.example.springdto.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeProjection findById(Long id) {
        return employeeRepository.findByIdUsingProjection(id);
    }

    public Employee save(Employee employee) {
        Employee employee1 = Employee.builder()
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .salary(employee.getSalary())
                .position(employee.getPosition())
                .department(departmentRepository.findByName(employee.getDepartment().getName()))
                        .build();
        employeeRepository.save(employee1);
        return employee1;
    }

    public Department createDepartment(Department department) {
        Department department1 = Department.builder()
                .name(department.getName())
                .build();
        departmentRepository.save(department1);
        return department1;
    }
    public Department findDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
    public List<EmployeeProjection> findAll() {
        return employeeRepository.findAllUsingProjection();
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public void update(Long id,Employee employee) {
        Employee employee1 = employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        employee1.setDepartment(employee.getDepartment());
        employee1.setFirstname(employee.getFirstname());
        employee1.setLastname(employee.getLastname());
        employee1.setSalary(employee.getSalary());
        employee1.setPosition(employee.getPosition());
        employeeRepository.save(employee1);
    }

}
