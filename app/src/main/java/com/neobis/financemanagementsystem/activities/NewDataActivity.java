package com.neobis.financemanagementsystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.Departments;
import com.neobis.financemanagementsystem.model.Projects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewDataActivity extends AppCompatActivity {

    private String BASE_URL = "https://neobisfms.herokuapp.com/";
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView textView;
    EditText department_et, counter_et, project_et, bankAccount_et;
    Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);

        textView = findViewById(R.id.textView);
        department_et = findViewById(R.id.department_et);
        counter_et = findViewById(R.id.counter_et);
        project_et = findViewById(R.id.projects_et);
        bankAccount_et = findViewById(R.id.bank_account_et);
        save_btn = findViewById(R.id.save_btn);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String depText, counterText, projectText, baText;
                depText = department_et.getText().toString().trim();
                counterText = counter_et.getText().toString().trim();
                projectText = project_et.getText().toString().trim();
                baText = bankAccount_et.getText().toString().trim();
                if(!depText.isEmpty()){
                    addNewDepartment(depText);
                }
                if(!counterText.isEmpty()){
                    addNewCounter(counterText);
                }
                if(!projectText.isEmpty()){
                    addNewProject(projectText);
                }
                if(!baText.isEmpty()){
                    addNewBankAccount(projectText);
                }
            }
        });
    }

    private void addNewDepartment(String departmentName) {
        final Departments department = new Departments(departmentName);
        Call<Departments> call = jsonPlaceHolderApi.createDepartment(department);
        call.enqueue(new Callback<Departments>() {
            @Override
            public void onResponse(Call<Departments> call, Response<Departments> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                Departments departmentResponse = response.body();
                String content = "";
                content += "Code" + response.code();
                content += "\n" + departmentResponse.getDepName() + "\n";
                textView.append(content);
                department_et.setText("");
            }

            @Override
            public void onFailure(Call<Departments> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mistake Occured", Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }

    private void addNewCounter(String counterName) {
        final Projects project = new Projects(counterName);
        Call<Projects> call = jsonPlaceHolderApi.createСounter(project);
        call.enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                Projects projectResponse = response.body();
                String content = "";
                content += "Code" + response.code();
                content += "\n" + projectResponse.getName() + "\n";
                textView.append(content);
                counter_et.setText("");
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mistake Occured", Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }

    private void addNewProject(String projectName) {
        final Projects project = new Projects(projectName);
        Call<Projects> call = jsonPlaceHolderApi.createProject(project);
        call.enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                Projects projectResponse = response.body();
                String content = "";
                content += "Code" + response.code();
                content += "\n" + projectResponse.getName() + "\n";
                textView.append(content);
                project_et.setText("");
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mistake Occured", Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }

    private void addNewBankAccount(String accountName) {
        Accounts account = new Accounts(accountName);
        Call<Accounts> call = jsonPlaceHolderApi.createAccount(account);
        call.enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                Accounts accountResponse = response.body();
                String content = "";
                content += "Code" + response.code();
                content += "\n" + accountResponse.getType() + "\n";
                textView.append(content);
                bankAccount_et.setText("");
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mistake Occured", Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }


}