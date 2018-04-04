package com.example.arya.netexam.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.function.Consumer;

public class Result {

    @SerializedName("results")
    @Expose
    private List<enumResult> results = null;

    public Result(List<enumResult> results) {
        this.results = results;
    }

    public List<enumResult> getResults() {
        return results;
    }

    public void setResults(List<enumResult> results) {
        this.results = results;
    }

    public int getYes() {
        int r = 0;
        for (enumResult result : results) {
            if (result == enumResult.YES) r++;
        }
        return r;
    }

    public int getNo() {
        int r = 0;
        for (enumResult result : results) {
            if (result == enumResult.NO) r++;
        }
        return r;
    }

    public int getNoAnswer() {
        int r = 0;
        for (enumResult result : results) {
            if (result == enumResult.NO_ANSWER) r++;
        }
        return r;
    }

    public enum enumResult {
        YES, NO, NO_ANSWER
    }
}
