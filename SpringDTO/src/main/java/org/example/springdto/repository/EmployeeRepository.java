package org.example.springdto.repository;

import org.example.springdto.dto.EmployeeProjection;
import org.example.springdto.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select concat(e.firstname, ' ', e.lastname) as fullName, e.position as position," +
            "d.name as departmentName from Employee e join e.department d")
    List<EmployeeProjection> findAllUsingProjection();
    @Query("select concat(e.firstname,' ',e.lastname)as fullName, e.position as position," +
            "d.name as departmentName from Employee e join e.department d where e.id =:employeeId")
    EmployeeProjection findByIdUsingProjection(@Param("employeeId")Long employeeId);
}
