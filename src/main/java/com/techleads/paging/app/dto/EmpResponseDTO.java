package com.techleads.paging.app.dto;

public class EmpResponseDTO {

    private int employeeId;
    private String employeeName;
    private int depId;
    private String departmentName;

    public EmpResponseDTO() {
    }

    public EmpResponseDTO(int employeeId, String employeeName, int depId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.depId = depId;
    }

    public EmpResponseDTO(int employeeId, String employeeName, int depId, String departmentName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.depId = depId;
        this.departmentName = departmentName;
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
}
