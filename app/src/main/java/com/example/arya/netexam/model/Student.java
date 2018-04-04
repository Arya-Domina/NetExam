package com.example.arya.netexam.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student extends User {

//    private String semester, group;

    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("group")
    @Expose
    private String group;

    public Student(String firstName, String lastName, String patronymic, String login, String password) {
        super(firstName, lastName, patronymic, login, password, UserType.student);
    }

    public Student(User user) {
        this(user.getFirstName(), user.getLastName(), user.getPatronymic(), user.getLogin(), user.getPassword());
    }

//    public Student(String firstName, String lastName, String patronymic, /*String login, String password, */String semester, String group) {
//        super(firstName, lastName, patronymic/*, login, password*/);
//        this.semester = semester;
//        this.group = group;
//    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
