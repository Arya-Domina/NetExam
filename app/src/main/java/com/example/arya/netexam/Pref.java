package com.example.arya.netexam;


import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Pref {

    private SharedPreferences preferences;
    private final String TOKEN = "token", USER_TYPE = "user_type";

    public Pref(Context context) {
        preferences = context.getSharedPreferences("data", MODE_PRIVATE);

    }

    public void saveTokenAndUserType(String token, String userType) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN, token);
        editor.putString(USER_TYPE, userType);
        editor.apply();
    }

    public String loadToken() {
        return preferences.getString(TOKEN, "");
    }

    public void clearToken() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(TOKEN);
        editor.apply();
    }

    public String loadUserType() {
        return preferences.getString(USER_TYPE, "");
    }

    public void saveIndex(String examName, int numberQuestion, int index) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(examName + numberQuestion, index);
        editor.apply();
    }

    public int loadIndex(String examName, int numberQuestion) {
        return preferences.getInt(examName + numberQuestion, -1);
    }

    public void clearIndex(String examName, int numberQuestion) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(examName + numberQuestion);
        editor.apply();
    }

}
