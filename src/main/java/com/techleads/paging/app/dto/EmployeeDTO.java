package com.techleads.paging.app.dto;

public class EmployeeDTO {

    private int employeeId;
    private String employeeName;
    private int depId;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int employeeId, String employeeName, int depId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.depId = depId;
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
}
