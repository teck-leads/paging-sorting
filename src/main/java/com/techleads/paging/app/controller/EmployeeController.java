package com.techleads.paging.app.controller;

import com.techleads.paging.app.dto.EmpResponseDTO;
import com.techleads.paging.app.dto.EmployeeDTO;
import com.techleads.paging.app.dto.EmployeePage;
import com.techleads.paging.app.entity.Employee;
import com.techleads.paging.app.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
     public List<Employee> getAllEmployees() {
         return employeeService.getAllEmployees();
     }

    @GetMapping("/employees/bydeptId/{deptId}")
    public List<EmpResponseDTO> getAllEmployees(@PathVariable("deptId") Integer deptId) {

        try {
            return employeeService.findAllEmployeesWithDepartment(deptId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/employees-page/bydeptId/{deptId}")
    public Page<EmpResponseDTO> findEmployeesByDepartmentWithPagination(
            @PathVariable("deptId") Integer deptId,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize
    ) {

        try {
            return employeeService.findEmployeesByDepartmentWithPagination(deptId, pageNo, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/employees-page-emp/bydeptId/{deptId}")
    public EmployeePage findEmployeesByDepartmentWithPaginationReturnEmployeePage(
            @PathVariable("deptId") Integer deptId,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize
    ) {

        try {
            return employeeService.findEmployeesByDepartmentWithPaginationReturnEmployeePage(deptId, pageNo, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/employees-page-sort-emp/bydeptId/{deptId}")
    public EmployeePage findEmployeesByDepartmentWithPaginationSortingReturnEmployeePage(
            @PathVariable("deptId") Integer deptId,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "d.department_name") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "DESC") String sortDirection

    ) {

        try {
            return employeeService.findEmployeesByDepartmentWithPaginationSortingReturnEmployeePage(deptId, pageNo, pageSize, sortBy, sortDirection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Example method to find an employee by ID
     @GetMapping("/employees/{id}")
     public Employee getEmployeeById(@PathVariable Integer id) {
         return employeeService.getEmployeeById(id);
     }
    // Example method to add a new employee
        @PostMapping("/employees/save")
        public Employee addEmployee(@RequestBody Employee employee){
            return employeeService.addEmployee(employee);
        }


    @PostMapping("/employees/save-employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO request){
        return employeeService.saveEmployee(request);
    }
}
