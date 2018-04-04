package com.example.arya.netexam.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teacher extends User {

//    private String position, department;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("department")
    @Expose
    private String department;

//    public Teacher(String firstName, String lastName, String patronymic, String login, String password, String position, String department) {
//        super(firstName, lastName, patronymic, login, password);
//        this.position = position;
//        this.department = department;
//    }

    public Teacher(String firstName, String lastName, String patronymic, String login, String password) {
        super(firstName, lastName, patronymic, login, password, UserType.teacher);
    }

    public Teacher(User user) {
        this(user.getFirstName(), user.getLastName(), user.getPatronymic(), user.getLogin(), user.getPassword());
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
