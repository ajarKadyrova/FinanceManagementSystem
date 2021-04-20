package com.neobis.financemanagementsystem.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.adapters.IncomeAdapter;
import com.neobis.financemanagementsystem.adapters.SettingsAdapter;
import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.CategoryOfExpense;
import com.neobis.financemanagementsystem.model.CategoryOfIncome;
import com.neobis.financemanagementsystem.model.CounterPartner;
import com.neobis.financemanagementsystem.model.Projects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsFragment2 extends Fragment {

    String BASE_URL = "https://neobisfms.herokuapp.com/";
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Toolbar toolbar;
    RecyclerView recyclerView;
    private List<Accounts> accounts = new ArrayList<>();
    private List<CounterPartner> counterPartners = new ArrayList<>();
    private List<Projects> projects = new ArrayList<>();
    private List<CategoryOfIncome> categoryOfIncomes = new ArrayList<>();
    private List<CategoryOfExpense> categoryOfExpenses = new ArrayList<>();
    String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings2, container, false);
        //toolbar = view.findViewById(R.id.toolBar_settings);
        recyclerView = view.findViewById(R.id.recyclerView_settings);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        token ="Token " + token;

//        SettingsFragment2Args args;
//        String message = "";
//        if(getArguments() != null) {
//            args = SettingsFragment2Args.fromBundle(getArguments());
//            message = args.getMessage();
//        }
//        if (message == "contragent"){
//            //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Бухгалтер");
//            getContragents();
//        }
        String key = getArguments().getString("key");
        if(key == "contragent"){
            getContragents();
        }
    }

    private void getContragents() {
        final Call<List<CounterPartner>> callCounterPartner = jsonPlaceHolderApi.getCounterPartner(token);
        callCounterPartner.enqueue(new Callback<List<CounterPartner>>() {
            @Override
            public void onResponse(Call<List<CounterPartner>> call, Response<List<CounterPartner>> response) {
                if (!response.isSuccessful()) {
                    //textView.setText("Code:" + response.code());
                    Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                    return;
                }
                counterPartners = response.body();
                //if (counterPartners != null) {
                    SettingsAdapter settingsAdapter = new SettingsAdapter(getContext(), counterPartners);
                    recyclerView.setAdapter(settingsAdapter);
                    Log.v("CAAAAAN", counterPartners.toString());
                //}
            }

            @Override
            public void onFailure(Call<List<CounterPartner>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                //textView.setText(t.getMessage());
            }
        });
    }
}
