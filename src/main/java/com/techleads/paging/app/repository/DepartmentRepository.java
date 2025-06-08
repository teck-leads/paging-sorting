package com.techleads.paging.app.repository;

import com.techleads.paging.app.entity.Department;
import com.techleads.paging.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // Custom query methods can be added here if needed
    // For example, to find employees by department or name
}
