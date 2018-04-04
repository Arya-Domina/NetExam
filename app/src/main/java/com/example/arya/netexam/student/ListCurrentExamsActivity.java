package com.example.arya.netexam.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.arya.netexam.App;
import com.example.arya.netexam.R;
import com.example.arya.netexam.model.Exam;
import com.example.arya.netexam.model.Exams;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListCurrentExamsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        ((TextView)findViewById(R.id.text_view)).setText(getResources().getString(R.string.list_current_exam));

        App.getApi().getCurrentExams().enqueue(new Callback<Exams>() {
            @Override
            public void onResponse(Call<Exams> call, Response<Exams> response) {
                try {
                    if(response.body() != null) {
                        Exams examList = response.body();
                        for (Exam exam : examList.getExams()) {
                            System.out.println("exam " + exam.getName());
                        }
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        ListExamsAdapter adapter = new ListExamsAdapter(ListCurrentExamsActivity.this, examList.getExams(), true);
                        recyclerView.setAdapter(adapter);

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
            public void onFailure(Call<Exams> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
