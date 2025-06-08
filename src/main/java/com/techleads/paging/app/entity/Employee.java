package com.techleads.paging.app.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "employees")
//@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor

public class Employee {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_Id")
    private int employeeId;


    @Column(name = "employee_name", length = 45, nullable = false)
    private String employeeName;


    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "dep_id")
    private Department department;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, Department department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}