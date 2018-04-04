package com.example.arya.netexam.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.arya.netexam.App;
import com.example.arya.netexam.MainActivity;
import com.example.arya.netexam.Pref;
import com.example.arya.netexam.R;
import com.example.arya.netexam.model.Exam;
import com.example.arya.netexam.model.Exams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_current_exams:
                intent = new Intent(StudentActivity.this, ListCurrentExamsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_passed_exams:
                intent = new Intent(StudentActivity.this, ListPassedExamsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_profile:

                return true;
            case R.id.action_sign_out:
                App.getApi().logout().enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.errorBody() == null) {
                            new Pref(StudentActivity.this).clearToken();
                            Intent intent = new Intent(StudentActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        App.getApi().getExams().enqueue(new Callback<Exams>() {
            @Override
            public void onResponse(Call<Exams> call, Response<Exams> response) {
                try {
                    if(response.body() != null) {
                        Exams examList = response.body();
                        for (Exam exam : examList.getExams()) {
                            System.out.println("exam " + exam.getName());
                        }
                        List<Exam> exams = setInitialMockData(examList.getExams());
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        ListExamsAdapter adapter = new ListExamsAdapter(StudentActivity.this, exams, false);
                        recyclerView.setAdapter(adapter);

                    }
                    else {
                        System.out.println("student no " + response.errorBody().string());
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Exams> call, Throwable t) {
                System.out.println("student err");
                t.printStackTrace();
                List<Exam> exams = setInitialMockData(new ArrayList<Exam>());
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                ListExamsAdapter adapter = new ListExamsAdapter(StudentActivity.this, exams, false);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private List<Exam> setInitialMockData(List<Exam> exams) {
        exams.add(new Exam(1, "Mock Матанализ", "Павел", "Иванов", "Иванович", "Кибернетика", "Преподаватель", 55, 40));
        exams.add(new Exam(2, "Mock Алгебра", "Павел", "Иванов", "Иванович", "Кибернетика", "Преподаватель", 55, 65));
        exams.add(new Exam(3, "Mock Безопасность", "Иван", "Павлов", "Павлович", "Безопасность", "Преподаватель", 55, 55));
        exams.add(new Exam(4, "Mock Ассемблер", "Павел", "Сергеев", "Сергеевич", "Безопасность", "Преподаватель", 55, 60));
        exams.add(new Exam(5, "Mock Программирование", "Дмитрий", "Петров", "Александрович", "Кибернетика", "Преподаватель", 55, 90));
        exams.add(new Exam(6, "Mock Сети", "Александр", "Александров", "Иванович", "Сети и телекоммуникации", "Преподаватель", 55, 1));
        return exams;
    }
}
