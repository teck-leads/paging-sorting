package com.techleads.paging.app.dto;

import java.io.Serializable;


public class UserDto implements Serializable {


    private Integer userId;
    private String name;
    private String email;
    private String street;
    private String city;
    private String country;

    public UserDto(Integer userId, String name, String email, String street, String city, String country) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public UserDto() {
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}

