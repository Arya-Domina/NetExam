package com.example.arya.netexam.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable{

//    private String firstName, lastName, patronymic;
//    private String login, password;
//    private UserType userType;
//    private String token;
//

//    public User(String firstName, String lastName, String patronymic, String login, String password) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.patronymic = patronymic;
//        this.login = login;
//        this.password = password;
//    }
//    @SerializedName("semester")
//    @Expose
//    private long semester;
//    @SerializedName("group")
//    @Expose
//    private String group;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("patronymic")
    @Expose
    private String patronymic;
    @SerializedName("userType")
    @Expose
    private UserType userType;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;

    public User(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
//        this.userType = userType;
    }

    public User(String firstName, String lastName, String patronymic, String login, String password, UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.userType = userType;
        this.login = login;
        this.password = password;
    }

    //    public long getSemester() {
//        return semester;
//    }
//
//    public void setSemester(long semester) {
//        this.semester = semester;
//    }
//
//    public String getGroup() {
//        return group;
//    }
//
//    public void setGroup(String group) {
//        this.group = group;
//    }

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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
