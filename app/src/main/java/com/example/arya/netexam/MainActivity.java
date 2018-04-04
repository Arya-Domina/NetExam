package com.example.arya.netexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.arya.netexam.model.Auth;
import com.example.arya.netexam.model.UserInfo;
import com.example.arya.netexam.model.UserType;
import com.example.arya.netexam.student.StudentActivity;

import java.io.IOException;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindViews({R.id.loginTIL, R.id.passwordTIL})
    List<TextInputLayout> textInputLayoutViews;

    @OnClick(R.id.entry)
    public void onClickEntry() {
        if (validate()) {
            App.getApi().login(new Auth(
                    textInputLayoutViews.get(0).getEditText().getText().toString(),
                    textInputLayoutViews.get(1).getEditText().getText().toString()))
                    .enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    try {
                        if(response.body() != null) {
                            UserInfo userInfo = response.body();

                            Pref pref = new Pref(MainActivity.this);
                            pref.saveTokenAndUserType(userInfo.getToken(), userInfo.getUser().getUserType().toString());
                            entry(userInfo.getUser().getUserType().toString());
                        }
                        else {
                            System.out.println("auth no " + response.errorBody().string());
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {
                    System.out.println("auth err");
                    t.printStackTrace();
                }
            });
        }
    }

    @OnClick(R.id.registration_main)
    public void onClickRegistration() {
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Pref pref = new Pref(MainActivity.this);
        String token = pref.loadToken();
        if (!token.isEmpty()) {
            System.out.println("no empty");
            entry(pref.loadUserType());
        } else {
            System.out.println("empty");
        }

    }

    private void entry(String userType) {
        Intent intent;
        if (userType.equals(UserType.teacher.toString())) {
            intent = new Intent(MainActivity.this, TeacherActivity.class);
        } else {
            intent = new Intent(MainActivity.this, StudentActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
