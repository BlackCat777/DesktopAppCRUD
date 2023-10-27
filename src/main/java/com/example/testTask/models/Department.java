package com.example.testTask.models;

public class Department {
    Integer id_Department;
    String department;
    String phone;
    String email;
    public Department(Integer id_Department, String department, String phone, String email) {
        this.id_Department = id_Department;
        this.department = department;
        this.phone = phone;
        this.email = email;
    }

    public Department(String department, String phone, String email) {
        this.department = department;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId_Department() {
        return id_Department;
    }

    public void setId_Department(Integer id_Department) {
        this.id_Department = id_Department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
