package com.techleads.paging.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "departments")
//@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor


public class Department {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_id")
    private int depId;


    @Column(name = "department_name", length = 25, nullable = false, unique = true)
    private String departmentName;


//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Employee> employees;


    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(int depId, String departmentName) {
        this.depId = depId;
        this.departmentName = departmentName;
    }

    public Department() {
    }
}
