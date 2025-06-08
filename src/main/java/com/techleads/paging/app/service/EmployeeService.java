package com.techleads.paging.app.service;

import com.techleads.paging.app.dto.EmpResponseDTO;
import com.techleads.paging.app.dto.EmployeeDTO;
import com.techleads.paging.app.dto.EmployeePage;
import com.techleads.paging.app.entity.Department;
import com.techleads.paging.app.entity.Employee;
import com.techleads.paging.app.repository.DepartmentRepository;
import com.techleads.paging.app.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }
    // This service can contain business logic related to Employee operations
    // For example, methods to add, update, delete, or retrieve employees

    // Example method to get all employees (this would typically call a repository)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Example method to find an employee by ID
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElse(null);
    }


    public Employee addEmployee(Employee employee) {

//        departmentRepository.findById(employee.getDepartment().getDepId())
//                .orElseThrow(() -> new RuntimeException("Department not found with id: " + employee.getDepartment().getDepId()));

        Optional<Department> byId =
                departmentRepository.findById(employee.getDepartment().getDepId());
        if(byId.isPresent()){
            employee.setDepartment(byId.get());
        } else {
            throw new RuntimeException("Department not found with id: " + employee.getDepartment().getDepId());
        }


        Employee saved = employeeRepository.save(employee);
        return saved;
    }


    public EmployeeDTO saveEmployee(EmployeeDTO dto){
        Optional<Department> byId =
                departmentRepository.findById(dto.getDepId());
        if(byId.isPresent()){
            Employee employee = new Employee();
            employee.setEmployeeId(dto.getEmployeeId());
            employee.setEmployeeName(dto.getEmployeeName());
            employee.setDepartment(byId.get());

            Employee saved = employeeRepository.save(employee);
            return new EmployeeDTO(saved.getEmployeeId(), saved.getEmployeeName(), saved.getDepartment().getDepId());
        } else {
            throw new RuntimeException("Department not found with id: " + dto.getDepId());
        }
    }

    public List<EmpResponseDTO> findAllEmployeesWithDepartment(Integer deptId) {
        List<Object[]> objsList = employeeRepository.findEmployeeByDepartmentId(deptId);
        /**
         *  List<EmpResponseDTO> empResponseDTOList = objsList.stream()
         *                 .map(obj -> new EmpResponseDTO((Integer) obj[0], (String) obj[1], (Integer) obj[2]))
         *                 .toList();
         */
        List<EmpResponseDTO> empResponseDTOList = new ArrayList<>();
        try {
            for(Object[] obj : objsList) {
                Integer employeeId = (Integer) obj[0];
                String employeeName = (String) obj[1];
                Integer departmentId = (Integer) obj[2];
                EmpResponseDTO dto =new EmpResponseDTO();
                dto.setEmployeeId(employeeId);
                dto.setEmployeeName(employeeName);
                dto.setDepId(departmentId);
                empResponseDTOList.add(dto);
            }
            return empResponseDTOList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Page<EmpResponseDTO> findEmployeesByDepartmentWithPagination(Integer departmentId, Integer pageNo, Integer pageSize) {
        try {
            Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNo);
            Page<Object[]> paginatedResult = employeeRepository.findEmployeeByDepartmentIdWithPagination(departmentId, pageable);

            Page<EmpResponseDTO> empResponseDTOPage = paginatedResult.map(obj -> {
                EmpResponseDTO dto = new EmpResponseDTO();
                dto.setEmployeeId((Integer) obj[0]);
                dto.setEmployeeName((String) obj[1]);
                dto.setDepId((Integer) obj[2]);
                return dto;
            });
            return empResponseDTOPage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EmployeePage findEmployeesByDepartmentWithPaginationReturnEmployeePage(Integer departmentId, Integer pageNo, Integer pageSize) {
        try {
            Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNo);
            Page<Object[]> paginatedResult = employeeRepository.findEmployeeByDepartmentIdWithPagination(departmentId, pageable);

            Page<EmpResponseDTO> empResponseDTOPage = paginatedResult.map(obj -> {
                EmpResponseDTO dto = new EmpResponseDTO();
                dto.setEmployeeId((Integer) obj[0]);
                dto.setEmployeeName((String) obj[1]);
                dto.setDepId((Integer) obj[2]);
                return dto;
            });

            EmployeePage employeePage = new EmployeePage();

            if(empResponseDTOPage.hasContent()) {

                employeePage.setEmployees(empResponseDTOPage.getContent());
                employeePage.setTotalPages(empResponseDTOPage.getTotalPages());
                employeePage.setTotalElements(empResponseDTOPage.getTotalElements());
                employeePage.setLast(empResponseDTOPage.isLast());
                employeePage.setSize(empResponseDTOPage.getSize());

                employeePage.setNumber(empResponseDTOPage.getNumber());
                employeePage.setNumberOfElements(empResponseDTOPage.getNumberOfElements());

            } else {
                employeePage.setEmployees(new ArrayList<>());
            }


            return employeePage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
