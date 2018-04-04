package com.example.arya.netexam.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ExamWithResults implements Serializable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("teacher")
    @Expose
    private Teacher teacher;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("results")
    @Expose
    private List<Result.enumResult> results = null;
//    private List<String> results = null;
//    private List<enumResult> results = null;

    public ExamWithResults(long id, Teacher teacher, String name, List<Result.enumResult> results) {
        this.id = id;
        this.teacher = teacher;
        this.name = name;
        this.results = results;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Result.enumResult> getResults() {
        return results;
    }

    public void setResults(List<Result.enumResult> results) {
        this.results = results;
    }
}
