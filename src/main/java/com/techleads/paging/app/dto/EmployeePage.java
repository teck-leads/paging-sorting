package com.techleads.paging.app.dto;

import java.util.List;

public class EmployeePage {

    private List<EmpResponseDTO> employees;

     private int totalPages;
    private Long totalElements;
    private boolean last;

    private int size;
    private int number;
    private int numberOfElements;

    public EmployeePage() {
    }

    public EmployeePage(List<EmpResponseDTO> employees, int totalPages, Long totalElements, boolean last, int size, int number, int numberOfElements) {
        this.employees = employees;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.last = last;
        this.size = size;
        this.number = number;
        this.numberOfElements = numberOfElements;
    }

    public List<EmpResponseDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmpResponseDTO> employees) {
        this.employees = employees;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
