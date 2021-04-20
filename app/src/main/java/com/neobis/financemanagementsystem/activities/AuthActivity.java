package com.neobis.financemanagementsystem.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.model.AuthToken;
import com.neobis.financemanagementsystem.model.Login;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthActivity extends AppCompatActivity {

    private String BASE_URL = "https://neobisfms.herokuapp.com/";
    JsonPlaceHolderApi jsonPlaceHolderApi;
    SharedPreferences sharedPreferences;
    EditText login_tiet, password_tiet;
    String login, password;
    Button signIn;
    TextView forgot_password;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        login_tiet = findViewById(R.id.login_tiet);
        password_tiet = findViewById(R.id.password_tiet);
        signIn = findViewById(R.id.signIn_btn);
        forgot_password = findViewById(R.id.forgot_password);
        sharedPreferences = getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = login_tiet.getText().toString().trim();
                password = password_tiet.getText().toString().trim();
                if(login.isEmpty()){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_no), Toast.LENGTH_LONG).show();
                } else if (password.isEmpty()){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.password_no), Toast.LENGTH_LONG).show();
                } else sendAuthData(login, password);
            }
        });
    }

    private void sendAuthData(String email, String password) {
        Login login = new Login(email, password);
        Call<AuthToken> call = jsonPlaceHolderApi.authUser(login);
        call.enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if (response.isSuccessful()) {
                    token = response.body().getToken();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Token", token);
                    editor.apply();
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else Toast.makeText(getApplicationContext(), getResources().getString(R.string.wrong_login), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.mistake), Toast.LENGTH_LONG).show();
            }
        });
    }
}
