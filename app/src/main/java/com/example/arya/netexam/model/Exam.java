package com.example.arya.netexam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Exam implements Serializable{
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("patronymic")
    @Expose
    private String patronymic;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("questionsCountPerExam")
    @Expose
    private long questionsCountPerExam;
    @SerializedName("timeInMinutes")
    @Expose
    private long timeInMinutes;

    public Exam(long id, String name, String firstName, String lastName, String patronymic, String department, String position, long questionsCountPerExam, long timeInMinutes) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.department = department;
        this.position = position;
        this.questionsCountPerExam = questionsCountPerExam;
        this.timeInMinutes = timeInMinutes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getQuestionsCountPerExam() {
        return questionsCountPerExam;
    }

    public void setQuestionsCountPerExam(long questionsCountPerExam) {
        this.questionsCountPerExam = questionsCountPerExam;
    }

    public long getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(long timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }
}
