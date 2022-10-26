package com.wormos.nalandaapp;

public class MyGrievanceModel {
    String date, description, subject, relation,status;

    public MyGrievanceModel() {
    }

    public MyGrievanceModel(String date, String description, String subject, String relation, String status) {
        this.date = date;
        this.description = description;
        this.subject = subject;
        this.relation = relation;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}