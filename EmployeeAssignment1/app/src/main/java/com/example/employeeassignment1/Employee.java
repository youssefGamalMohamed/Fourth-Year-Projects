package com.example.employeeassignment1;

import java.io.Serializable;

public class Employee implements Serializable {
    public String Name , Title , Phone , Email , DepartmentName;
    public Employee(String name, String title, String phone, String email,String departmentName) {
        Name = name;
        Title = title;
        Phone = phone;
        Email = email;
        DepartmentName = departmentName;
    }
    public Employee(){
        Name = "";
        Title = "";
        Phone = "";
        Email = "";
        DepartmentName = "";

    }
}
