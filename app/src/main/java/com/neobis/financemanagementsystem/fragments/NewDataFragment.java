package com.neobis.financemanagementsystem.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.CategoryOfExpense;
import com.neobis.financemanagementsystem.model.CategoryOfIncome;
import com.neobis.financemanagementsystem.model.PostIncome;
import com.neobis.financemanagementsystem.model.Projects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewDataFragment extends Fragment {

    String BASE_URL = "https://neobisfms.herokuapp.com/";
    EditText editTextNewData;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Button save_btn;
    String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_view_settings2, container, false);
        editTextNewData = view.findViewById(R.id.newData_tiet);
        save_btn = view.findViewById(R.id.save_btn_newData);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        token ="Token " + token;

        String key = getArguments().getString("key");
        if(key == "contragent"){
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = editTextNewData.getText().toString();
                    postContrAgent();
                }
            });
        }
        else if (key == "account"){
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = editTextNewData.getText().toString();
                    postAccount();
                }
            });
        }
        else if (key == "categoryIncome"){
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = editTextNewData.getText().toString();
                    postCategoryIncome();
                }
            });
        }
        else if (key == "categoryExpense"){
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = editTextNewData.getText().toString();
                    postCategoryExpense();
                }
            });
        }
        else if (key == "project"){
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = editTextNewData.getText().toString();
                    postProject();
                }
            });
        }
        return view;
    }

    private void postProject() {
        Projects projects = null;
        String data = editTextNewData.getText().toString().trim();
        if(data.isEmpty()) Toast.makeText(getContext(), "Введите имя контрагента", Toast.LENGTH_SHORT).show();
        else projects = new Projects(data);
        Call<Projects> accountsCall = jsonPlaceHolderApi.createProject(token, projects);
        accountsCall.enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                }
                Projects projectsResponse = response.body();
                Toast.makeText(getContext(), getResources().getString(R.string.saved_data),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postCategoryExpense() {
        CategoryOfExpense categoryOfExpense = null;
        String data = editTextNewData.getText().toString().trim();
        if(data.isEmpty()) Toast.makeText(getContext(), "Введите имя контрагента", Toast.LENGTH_SHORT).show();
        else categoryOfExpense = new CategoryOfExpense(data);
        Call<CategoryOfExpense> accountsCall = jsonPlaceHolderApi.createCategoryExpense(token, categoryOfExpense);
        accountsCall.enqueue(new Callback<CategoryOfExpense>() {
            @Override
            public void onResponse(Call<CategoryOfExpense> call, Response<CategoryOfExpense> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                }
                CategoryOfExpense expensesResponse = response.body();
                Toast.makeText(getContext(), getResources().getString(R.string.saved_data),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<CategoryOfExpense> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postCategoryIncome() {
        CategoryOfIncome categoryOfIncome = null;
        String data = editTextNewData.getText().toString().trim();
        if(data.isEmpty()) Toast.makeText(getContext(), "Введите имя контрагента", Toast.LENGTH_SHORT).show();
        else categoryOfIncome = new CategoryOfIncome(data);
        Call<CategoryOfIncome> accountsCall = jsonPlaceHolderApi.createCategoryIncome(token, categoryOfIncome);
        accountsCall.enqueue(new Callback<CategoryOfIncome>() {
            @Override
            public void onResponse(Call<CategoryOfIncome> call, Response<CategoryOfIncome> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                }
                CategoryOfIncome incomesResponse = response.body();
                Toast.makeText(getContext(), getResources().getString(R.string.saved_data),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CategoryOfIncome> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postAccount() {
        Accounts accounts = null;
        String data = editTextNewData.getText().toString().trim();
        if(data.isEmpty()) Toast.makeText(getContext(), "Введите имя контрагента", Toast.LENGTH_SHORT).show();
        else accounts = new Accounts(data);
        Call<Accounts> accountsCall = jsonPlaceHolderApi.createAccount(token, accounts);
        accountsCall.enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                }
                Accounts accountsResponse = response.body();
                Toast.makeText(getContext(), getResources().getString(R.string.saved_data),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postContrAgent() {
        Projects projects = null;
        String data = editTextNewData.getText().toString().trim();
        if(data.isEmpty()) Toast.makeText(getContext(), "Введите имя контрагента", Toast.LENGTH_SHORT).show();
        else projects = new Projects(data);
        Call<Projects> accountsCall = jsonPlaceHolderApi.createСounter(token, projects);
        accountsCall.enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                }
                Projects projectsResponse = response.body();
                Toast.makeText(getContext(), getResources().getString(R.string.saved_data),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        
    }
}
