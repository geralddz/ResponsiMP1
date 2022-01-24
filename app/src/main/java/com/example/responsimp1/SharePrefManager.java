package com.example.responsimp1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SharePrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences("responsi", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getUsername() {
        return sharedPreferences.getString("sp_username","GeraldDzA");
    }
    public String getPassword() {
        return sharedPreferences.getString("sp_password", "20.02.0508");
    }

    public void saveIsLogin (Boolean value) {
        editor.putBoolean("sp_islogin", value);
        editor.commit();
        editor.apply();
    }

}
