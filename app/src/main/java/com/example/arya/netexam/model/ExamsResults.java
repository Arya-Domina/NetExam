package com.example.arya.netexam.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ExamsResults implements Serializable{

    @SerializedName("exams")
    @Expose
    private List<ExamWithResults> exams = null;

    public ExamsResults(List<ExamWithResults> exams) {
        this.exams = exams;
    }

    public List<ExamWithResults> getExams() {
        return exams;
    }

    public void setExams(List<ExamWithResults> exams) {
        this.exams = exams;
    }
}
