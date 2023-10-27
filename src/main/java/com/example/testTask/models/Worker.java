package com.example.testTask.models;

public class Worker {
    private Integer idWorker;
    private String name;
    private String surname;
    private String patronymic;
    private Integer idJobTitle;
    private Integer idDepartment;

    public Worker(Integer idWorker, String name, String surname, String patronymic, Integer idJobTitle, Integer idDepartment) {
        this.idWorker = idWorker;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.idJobTitle = idJobTitle;
        this.idDepartment = idDepartment;
    }

    public Worker(String name, String surname, String patronymic, Integer idJobTitle, Integer idDepartment) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.idJobTitle = idJobTitle;
        this.idDepartment = idDepartment;
    }

    public Integer getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(Integer idWorker) {
        this.idWorker = idWorker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getIdJobTitle() {
        return idJobTitle;
    }

    public void setIdJobTitle(Integer idJobTitle) {
        this.idJobTitle = idJobTitle;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }
}
