package com.example.testTask.models;

public class JobTitle {
    Integer id_Title;
    String job_Title;
    Integer salary;

    public JobTitle(String job_Title, Integer salary) {
        this.job_Title = job_Title;
        this.salary = salary;
    }

    public JobTitle(Integer id_Title, String job_Title, Integer salary) {
        this.id_Title = id_Title;
        this.job_Title = job_Title;
        this.salary = salary;
    }

    public Integer getId_Title() {
        return id_Title;
    }

    public void setId_Title(Integer id_Title) {
        this.id_Title = id_Title;
    }

    public String getJob_Title() {
        return job_Title;
    }

    public void setJob_Title(String job_Title) {
        this.job_Title = job_Title;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
