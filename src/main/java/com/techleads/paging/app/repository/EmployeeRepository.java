package com.techleads.paging.app.repository;

import com.techleads.paging.app.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Custom query methods can be added here if needed
    // For example, to find employees by department or name
    @Query(value = "SELECT e.employee_Id, e.employee_name, e.department_id FROM tech_leads.employees e, tech_leads.departments d  where e.department_id=d.dep_id and d.dep_id = :departmentId", nativeQuery = true)
    List<Object[]> findEmployeeByDepartmentId(@Param("departmentId") Integer departmentId);


    @Query(value = "SELECT e.employee_Id, e.employee_name, e.department_id, d.department_name FROM tech_leads.employees e, tech_leads.departments d WHERE e.department_id = d.dep_id AND d.dep_id = :departmentId",
            countQuery = "SELECT COUNT(*) FROM tech_leads.employees e, tech_leads.departments d WHERE e.department_id = d.dep_id AND d.dep_id = :departmentId",
            nativeQuery = true)
    Page<Object[]> findEmployeeByDepartmentIdWithPagination1(@Param("departmentId") Integer departmentId, Pageable pageable);


 //Query modified to use JOIN syntax for sorting issue, now sorting department_name column is working
    @Query(value = "SELECT e.employee_Id, e.employee_name, e.department_id, d.department_name FROM tech_leads.employees e JOIN tech_leads.departments d ON e.department_id = d.dep_id WHERE d.dep_id = :departmentId",
            countQuery = "SELECT COUNT(*) FROM tech_leads.employees e JOIN tech_leads.departments d ON e.department_id = d.dep_id WHERE d.dep_id = :departmentId",
            nativeQuery = true)
    Page<Object[]> findEmployeeByDepartmentIdWithPagination(@Param("departmentId") Integer departmentId, Pageable pageable);
}




