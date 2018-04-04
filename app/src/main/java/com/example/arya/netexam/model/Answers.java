package com.example.arya.netexam.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Answers {

    @SerializedName("answers")
    @Expose
    private List<Integer> answers = null;

    public Answers() {
    }

    public Answers(List<Integer> answers) {
        super();
        this.answers = answers;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

}
