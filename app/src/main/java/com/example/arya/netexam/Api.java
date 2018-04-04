package com.example.arya.netexam;


import com.example.arya.netexam.model.Answers;
import com.example.arya.netexam.model.Auth;
import com.example.arya.netexam.model.Exams;
import com.example.arya.netexam.model.ExamsResults;
import com.example.arya.netexam.model.Questions;
import com.example.arya.netexam.model.Result;
import com.example.arya.netexam.model.Student;
import com.example.arya.netexam.model.Teacher;
import com.example.arya.netexam.model.Time;
import com.example.arya.netexam.model.UserInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @POST("/api/teacher")
    Call<UserInfo> registrationTeacher(@Body Teacher teacher);

    @POST("/api/student")
    Call<UserInfo> registrationStudent(@Body Student student);

    @POST("/api/session")
    Call<UserInfo> login(@Body Auth auth);

    @DELETE("/api/session")
    Call<ResponseBody> logout();

    @GET("/api/exams")
    Call<Exams> getExams();

    @GET("/api/exams/current")
    Call<Exams> getCurrentExams();

    @GET("/api/exams/{number}/questions")
    Call<Questions> getQuestions(@Path("number") int number);

    @GET("/api/exams/{number}")
    Call<Time> getTime(@Path("number") int number);

    @POST("/api/exams/{number}/solutions")
    Call<Result> sendAnswers(@Path("number") int number, @Body Answers answers);

    @GET("/api/exams/solutions")
    Call<ExamsResults> getExamsResults();

}
