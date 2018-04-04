package com.example.arya.netexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class RegistrationActivity extends AppCompatActivity {

    @BindViews({R.id.loginTIL, R.id.passwordTIL})
    List<TextInputLayout> textInputLayoutViews;
    @BindView(R.id.passwordTIL)
    TextInputLayout password;
    @BindView(R.id.repeat_passwordTIL)
    TextInputLayout rePassword;

    @OnClick({R.id.student_btn, R.id.teacher_btn})
    public void onClickStudentBtn(Button button) {
        if (validate()) {
            Intent intent = new Intent(RegistrationActivity.this, RegistrationActivityTwo.class);
            Bundle bundle = new Bundle();
            bundle.putString(getResources().getString(R.string.loginEN), textInputLayoutViews.get(0).getEditText().getText().toString());
            bundle.putString(getResources().getString(R.string.passwordEN), textInputLayoutViews.get(1).getEditText().getText().toString());
            Boolean check = button.getText() == getResources().getString(R.string.teacher);
            bundle.putBoolean(getResources().getString(R.string.type), check);

            intent.putExtras(bundle);
            startActivity(intent);
        }
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
        return (rePassword.getError() == null) && (check);
    }

    @OnTextChanged({R.id.password, R.id.repeat_password})
    void onTextChanged() {
        if (!password.getEditText().getText().toString().equals(rePassword.getEditText().getText().toString())) {
            rePassword.setError(getResources().getString(R.string.must_be_equals));
        } else {
            rePassword.setError(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

    }
}
