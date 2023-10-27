package com.example.testTask.models;

public class Lead {
    Integer idLead;
    Integer idWorker;
    Integer idDepartment;

    public Lead(Integer idLead, Integer idWorker, Integer idDepartment) {
        this.idLead = idLead;
        this.idWorker = idWorker;
        this.idDepartment = idDepartment;
    }

    public Lead(Integer idWorker, Integer idDepartment) {
        this.idWorker = idWorker;
        this.idDepartment = idDepartment;
    }

    public Integer getIdLead() {
        return idLead;
    }

    public void setIdLead(Integer idLead) {
        this.idLead = idLead;
    }

    public Integer getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(Integer idWorker) {
        this.idWorker = idWorker;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }
}
