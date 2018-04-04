package com.example.arya.netexam.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arya.netexam.R;
import com.example.arya.netexam.model.Exam;
import com.example.arya.netexam.model.ExamWithResults;

import java.util.List;


class ListExamsAdapter extends RecyclerView.Adapter<ListExamsAdapter.ViewHolder> {


//    private LayoutInflater inflater;
    private Context context;
    private List<Exam> exams;
    private List<ExamWithResults> examsWithResult;
    private boolean current;
    private boolean passed;

    ListExamsAdapter(Context context, List<Exam> exams, boolean current) {
        this.exams = exams;
        this.context = context;
        this.current = current;
        passed = false;
//        inflater = LayoutInflater.from(context);
    }

    ListExamsAdapter(Context context, List<ExamWithResults> examsWithResult) {
        this.context = context;
        this.examsWithResult = examsWithResult;
        passed = true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_exams_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(passed) {
            final ExamWithResults exam = examsWithResult.get(position);
            holder.name.setText(exam.getName());
            holder.teacher.setText(
                    context.getResources().getString(R.string.teacher) + ": " +
                            exam.getTeacher().getFirstName() + " " +
                            exam.getTeacher().getLastName() + " " +
                            exam.getTeacher().getPatronymic());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ExamInfoActivity.class);
                    intent.putExtra("exam", exam);
                    intent.putExtra("passed", true);
                    context.startActivity(intent);
                }
            });
        } else {
            final Exam exam = exams.get(position);
            holder.name.setText(exam.getName());
            holder.teacher.setText(
                    context.getResources().getString(R.string.teacher) + ": " +
                            exam.getFirstName() + " " +
                            exam.getLastName() + " " +
                            exam.getPatronymic());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    if (current) {
                        intent = new Intent(context, PassingExamActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(context.getResources().getString(R.string.exam_name), exam.getName());
                        bundle.putInt(context.getResources().getString(R.string.exam_number), (int) exam.getId());
                        intent.putExtras(bundle);
                    } else {
                        intent = new Intent(context, ExamInfoActivity.class);
                        intent.putExtra("exam", exam);
                        intent.putExtra("passed", false);
                    }
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return passed ? examsWithResult.size() : exams.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, teacher;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            teacher = (TextView) itemView.findViewById(R.id.teacher);
        }

    }
}
