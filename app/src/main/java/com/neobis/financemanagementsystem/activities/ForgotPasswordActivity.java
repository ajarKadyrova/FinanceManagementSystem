package com.neobis.financemanagementsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.model.EmailResetPassword;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPasswordActivity extends AppCompatActivity {

    private String BASE_URL = "https://neobisfms.herokuapp.com/";
    JsonPlaceHolderApi jsonPlaceHolderApi;
    SharedPreferences sharedPreferences;
    EditText email_tiet;
    String email;
    Button reset_password_btn;
    TextView authorize;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email_tiet = findViewById(R.id.email_tiet);
        reset_password_btn = findViewById(R.id.reset_password_btn);
        authorize = findViewById(R.id.authorize);
        sharedPreferences = getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        token ="Token " + token;

        authorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });

        reset_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_tiet.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_no), Toast.LENGTH_LONG).show();
                } else sendResetPasswordData(email);
            }
        });
    }

    private void sendResetPasswordData(String email) {
        EmailResetPassword emailResetPassword = new EmailResetPassword(email);
        Call<EmailResetPassword> call = jsonPlaceHolderApi.resetPassword(token, emailResetPassword);
        call.enqueue(new Callback<EmailResetPassword>() {
            @Override
            public void onResponse(Call<EmailResetPassword> call, Response<EmailResetPassword> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.wrong_email), Toast.LENGTH_LONG).show();
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Token", token);
                editor.apply();
                Toast.makeText(getApplicationContext(), "Данные отправлены", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ForgotPasswordActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onFailure(Call<EmailResetPassword> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.mistake), Toast.LENGTH_LONG).show();
            }
        });
    }
}