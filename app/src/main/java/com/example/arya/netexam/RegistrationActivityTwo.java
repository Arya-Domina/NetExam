package com.example.arya.netexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arya.netexam.model.Student;
import com.example.arya.netexam.model.Teacher;
import com.example.arya.netexam.model.User;
import com.example.arya.netexam.model.UserInfo;
import com.example.arya.netexam.model.UserType;
import com.example.arya.netexam.student.StudentActivity;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationActivityTwo extends AppCompatActivity {

    @BindView(R.id.text1) TextView text1;
    @BindView(R.id.text2) TextView text2;
    @BindView(R.id.spinner1) Spinner spinner1;
    @BindView(R.id.spinner2) Spinner spinner2;
    @BindViews({R.id.nameTIL, R.id.lastnameTIL}) List<TextInputLayout> textInputLayoutViews;
    @BindView(R.id.patronymicTIL) TextInputLayout patronymicTIL;

    Bundle bundle;

    @OnClick(R.id.registration_btn)
    public void OnClick() {
        if(validate()) {
            User user = new User(textInputLayoutViews.get(0).getEditText().getText().toString(),
                    textInputLayoutViews.get(1).getEditText().getText().toString(),
                    patronymicTIL.getEditText().getText().toString());
            user.setLogin(bundle.getString(getResources().getString(R.string.loginEN)));
            user.setPassword(bundle.getString(getResources().getString(R.string.passwordEN)));

            if (bundle.getBoolean(getResources().getString(R.string.type))) {
                Teacher teacher = new Teacher(user);
                teacher.setDepartment(spinner1.getSelectedItem().toString());
                teacher.setPosition(spinner2.getSelectedItem().toString());

                App.getApi().registrationTeacher(teacher).enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        if(response.body() != null) {
                            save(response.body());
                            start(response.body());
                        }
                        else try {
                            System.out.println("teacher no " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        System.out.println("teacher err");
                        t.printStackTrace();
                    }
                });
            } else {
                Student student = new Student(user);
                student.setSemester(spinner1.getSelectedItem().toString());
                student.setGroup(spinner2.getSelectedItem().toString());

                App.getApi().registrationStudent(student).enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        if (response.body() != null) {
                            save(response.body());
                            start(response.body());
                        }
                        else try {
                            System.out.println("student no " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        System.out.println("student err");
                        t.printStackTrace();
                    }
                });
            }
        }
    }

    private void start(UserInfo body) {
        Intent intent;
        if (body.getUser().getUserType().toString().equals(UserType.teacher.toString())) {
            intent = new Intent(RegistrationActivityTwo.this, TeacherActivity.class);
        } else {
            intent = new Intent(RegistrationActivityTwo.this, StudentActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void save(UserInfo body) {
        Pref pref = new Pref(this);
        pref.saveTokenAndUserType(body.getToken(), body.getUser().getUserType().toString());
    }

    public boolean validate() {
        boolean check = true;
        for (TextInputLayout view : textInputLayoutViews) {
            if (view.getEditText().getText().toString().isEmpty()) {
                view.setError(view.getResources().getString(R.string.must_not_be_empty));
                check = false;
            } else {
                view.setError(null);
            }
        }
        return check;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();
        if (bundle.getBoolean(getResources().getString(R.string.type))) {
            text1.setText(R.string.department);
            text2.setText(R.string.position);
            applySpinner(R.array.departments, spinner1);
            applySpinner(R.array.positions, spinner2);
        } else {
            text1.setText(R.string.semester);
            text2.setText(R.string.group);
            applySpinner(R.array.semesters, spinner1);
            applySpinner(R.array.groups, spinner2);
        }

    }

    private void applySpinner(int resArray, Spinner spinner) {
        String string[] = getResources().getStringArray(resArray);
        ArrayAdapter<String> spinnerArrayAdapter;
        spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, string);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }
}
