package com.example.arya.netexam.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.arya.netexam.App;
import com.example.arya.netexam.R;
import com.example.arya.netexam.model.ExamsResults;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListPassedExamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        ((TextView)findViewById(R.id.text_view)).setText(getResources().getString(R.string.list_passed_exam));
        App.getApi().getExamsResults().enqueue(new Callback<ExamsResults>() {
            @Override
            public void onResponse(Call<ExamsResults> call, Response<ExamsResults> response) {
                try {
                    if(response.body() != null) {
                        ExamsResults examsResults = response.body();
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        ListExamsAdapter adapter = new ListExamsAdapter(ListPassedExamsActivity.this, examsResults.getExams());
                        recyclerView.setAdapter(adapter);
                    } else {
                        System.out.println(response.errorBody().string());
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ExamsResults> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
