package com.neobis.financemanagementsystem.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.neobis.financemanagementsystem.R;

public class AuthActivity extends AppCompatActivity {

    EditText login, password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        login = findViewById(R.id.login_edittext);
        password = findViewById(R.id.password_edittext);
        signIn = findViewById(R.id.signIn_btn);

    }
}
