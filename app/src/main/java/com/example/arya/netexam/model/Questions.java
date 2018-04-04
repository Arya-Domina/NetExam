package com.example.arya.netexam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Questions {
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;

    public Questions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public class Question implements Serializable{
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("number")
        @Expose
        private long number;
        @SerializedName("id")
        @Expose
        private long id;
        @SerializedName("answers")
        @Expose
        private List<String> answers = null;
        @SerializedName("correct")
        @Expose
        private long correct;

        public Question(String question, long number, long id, List<String> answers, long correct) {
            this.question = question;
            this.number = number;
            this.id = id;
            this.answers = answers;
            this.correct = correct;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public long getNumber() {
            return number;
        }

        public void setNumber(long number) {
            this.number = number;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public void setAnswers(List<String> answers) {
            this.answers = answers;
        }

        public long getCorrect() {
            return correct;
        }

        public void setCorrect(long correct) {
            this.correct = correct;
        }

    }
}



