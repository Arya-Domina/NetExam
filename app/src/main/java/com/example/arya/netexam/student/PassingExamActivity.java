package com.example.arya.netexam.student;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.arya.netexam.App;
import com.example.arya.netexam.Pref;
import com.example.arya.netexam.R;
import com.example.arya.netexam.model.Answers;
import com.example.arya.netexam.model.Questions;
import com.example.arya.netexam.model.Result;
import com.example.arya.netexam.model.Time;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassingExamActivity extends AppCompatActivity {

    @BindView(R.id.pager) ViewPager viewPager;
    @BindView(R.id.exam_name) TextView textView;
    @BindView(R.id.timer) TextView timer;
    private Questions questions;
    private String examName;
    private int examNumber;
    private Time time;

    @OnClick(R.id.send_answers)
    public void onClick() {

        List<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < questions.getQuestions().size(); i++) {
            Pref pref = new Pref(PassingExamActivity.this);
            int index = pref.loadIndex(examName, i);
            answerList.add(index != -1 ? index : null);
        }
        Answers answers = new Answers(answerList);
        for (int i = 0; i < answers.getAnswers().size(); i++) {
            System.out.println(answers.getAnswers().get(i));
        }
        sendAnswers(answers);
        clearInd();
        finish();
    }

    private void clearInd() {
        for (int i = 0; i < questions.getQuestions().size(); i++) {
            new Pref(PassingExamActivity.this).clearIndex(examName, i);
        }
    }

    private void sendAnswers(Answers answers) {
        App.getApi().sendAnswers(examNumber, answers).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void getQuestion() {
        App.getApi().getQuestions(examNumber).enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(Call<Questions> call, Response<Questions> response) {
                System.out.println("start");
                try {
                    System.out.println("try");
                    if(response.body() != null) {
                        System.out.println("if");
                        questions = response.body();

                        PassingExamPagerAdapter pagerAdapter = new PassingExamPagerAdapter(getSupportFragmentManager(), questions, examName);
                        viewPager.setAdapter(pagerAdapter);

                        getTime();
                    }
                    else {
                        System.out.println(response.errorBody().string());
                        System.out.println("else");
                    }
                }
                catch (IOException e) {
                    System.out.println("catch exception");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Questions> call, Throwable t) {
                System.out.println("Failure");
                t.printStackTrace();
            }
        });
    }

    private void getTime() {
        App.getApi().getTime(examNumber).enqueue(new Callback<Time>() {
            @Override
            public void onResponse(Call<Time> call, Response<Time> response) {
                try {
                    if(response.body() != null) {
                        time = response.body();
                        startTimer();
                    }
                    else {
                        System.out.println(response.errorBody().string());
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Time> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_exam);
        ButterKnife.bind(this);

        examNumber = getIntent().getExtras().getInt(getResources().getString(R.string.exam_number));
        examName = getIntent().getExtras().getString(getResources().getString(R.string.exam_name));
        textView.setText(examName);

        getQuestion();
        System.out.println("get Q");

    }

    private void startTimer() {
        new CountDownTimer(time.getTimeInMinutes() * 60000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText("Осталось: " + String.format(Locale.ROOT, "%.2f", (double)l/60000) +" минут");
            }
            @Override
            public void onFinish() {
                clearInd();
            }
        }.start();
    }
}
