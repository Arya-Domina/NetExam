package com.example.arya.netexam.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arya.netexam.R;
import com.example.arya.netexam.model.Exam;
import com.example.arya.netexam.model.ExamWithResults;
import com.example.arya.netexam.model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExamInfoActivity extends AppCompatActivity {

    Exam exam;
    ExamWithResults examWithResults;

    @BindView(R.id.name) TextView name;
    @BindView(R.id.position) TextView position;
    @BindView(R.id.teacher_name) TextView teacher;
    @BindView(R.id.department) TextView department;
    @BindView(R.id.questions_count) TextView questions_count;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.start_exam) Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_info);
        ButterKnife.bind(this);

        if(getIntent().getBooleanExtra("passed", false)) {
            examWithResults = ((ExamWithResults) getIntent().getExtras().get("exam"));

            name.setText(examWithResults.getName());
            position.setText(examWithResults.getTeacher().getPosition());
            teacher.setText(examWithResults.getTeacher().getFirstName() + " " +
                    examWithResults.getTeacher().getLastName() + " " +
                    examWithResults.getTeacher().getPatronymic());
            department.setText(examWithResults.getTeacher().getDepartment());
            Result result = new Result(examWithResults.getResults());
            questions_count.setText("Правильных ответов " + result.getYes() + " из " + result.getResults().size());
            time.setText("Вопросов без ответа " + result.getNoAnswer());

            button.setVisibility(View.INVISIBLE);
        } else {
            exam = ((Exam) getIntent().getExtras().get("exam"));

            name.setText(exam.getName());
            position.setText(exam.getPosition());
            teacher.setText(exam.getFirstName() + " " + exam.getLastName() + " " + exam.getPatronymic());
            department.setText(getResources().getString(R.string.department) + ": " + exam.getDepartment());
            questions_count.setText(getResources().getString(R.string.questions_count) + ": " + (Long.toString(exam.getQuestionsCountPerExam())));
            time.setText(getResources().getString(R.string.time) + ": " + (Long.toString(exam.getTimeInMinutes())));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ExamInfoActivity.this, PassingExamActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(getResources().getString(R.string.exam_name), exam.getName());
                    bundle.putInt(getResources().getString(R.string.exam_number), (int) exam.getId());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }




    }
}
