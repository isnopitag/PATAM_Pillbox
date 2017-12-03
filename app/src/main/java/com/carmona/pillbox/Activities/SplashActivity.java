package com.carmona.pillbox.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.carmona.pillbox.Activities.MainActivity;
import com.carmona.pillbox.Models.Login;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
