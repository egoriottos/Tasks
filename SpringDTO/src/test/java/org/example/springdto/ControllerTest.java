package org.example.springdto;

import org.example.springdto.controller.Controller;
import org.example.springdto.dto.EmployeeProjection;
import org.example.springdto.entity.Department;
import org.example.springdto.entity.Employee;
import org.example.springdto.repository.DepartmentRepository;
import org.example.springdto.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ControllerTest {
    @InjectMocks
    private Controller controller;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private DepartmentRepository repository;

    @Test
    public void testCreateDepartment(){
        MockitoAnnotations.initMocks(this);
        Department department = Department.builder().name("Department Name").build();
        when(employeeService.createDepartment(department)).thenReturn(department);
        ResponseEntity<Department> responseEntity = controller.createDepartment(department);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(department, responseEntity.getBody());
    }

    @Test
    public void testCreateEmployee(){
        MockitoAnnotations.initMocks(this);
        Department department = Department.builder()
                .id(1L)
                .name("Department Name").build();
        Employee employee = Employee.builder()
                .id(1L)
                .firstname("vova")
                .lastname("vovav")
                .position("jun")
                .salary(3000)
                .department(department)
                .build();
        when(employeeService.save(employee)).thenReturn(employee);
        ResponseEntity<Employee> response = controller.create(employee);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(employee, response.getBody());
    }
    @Test
    public void testFindById(){
        MockitoAnnotations.initMocks(this);
        EmployeeProjection expectedProjection = new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "vova";
            }

            @Override
            public String getPosition() {
                return "jun";
            }

            @Override
            public String getDepartmentName() {
                return "vovav";
            }
        };
        Department department = Department.builder()
                .id(1L)
                .name("Department Name").build();
        Employee employee = Employee.builder()
                .id(1L)
                .firstname("vova")
                .lastname("vovav")
                .position("jun")
                .salary(3000)
                .department(department)
                .build();
        when(employeeService.findById(employee.getId())).thenReturn(expectedProjection);
        EmployeeProjection actualProjection = employeeService.findById(employee.getId());
        assertEquals(expectedProjection.getFullName(), actualProjection.getFullName());
        assertEquals(expectedProjection.getPosition(), actualProjection.getPosition());
        assertEquals(expectedProjection.getDepartmentName(), actualProjection.getDepartmentName());

    }

    @Test
    public void testFindAll(){
        MockitoAnnotations.initMocks(this);
        EmployeeProjection expectedProjection = new EmployeeProjection() {
            @Override
            public String getFullName() {
                return "vova";
            }

            @Override
            public String getPosition() {
                return "jun";
            }

            @Override
            public String getDepartmentName() {
                return "vovav";
            }
        };
        Department department = Department.builder()
                .id(1L)
                .name("Department Name").build();
        Employee employee = Employee.builder()
                .id(1L)
                .firstname("vova")
                .lastname("vovav")
                .position("jun")
                .salary(3000)
                .department(department)
                .build();
        when(employeeService.findAll()).thenReturn(List.of(expectedProjection));
        List<EmployeeProjection> actualProjections = employeeService.findAll();
        assertEquals(expectedProjection.getFullName(), actualProjections.get(0).getFullName());
        assertEquals(expectedProjection.getPosition(), actualProjections.get(0).getPosition());
        assertEquals(expectedProjection.getDepartmentName(), actualProjections.get(0).getDepartmentName());

    }
}
