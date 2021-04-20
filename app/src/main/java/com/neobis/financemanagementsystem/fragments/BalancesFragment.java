package com.neobis.financemanagementsystem.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.adapters.BalancesAdapter;
import com.neobis.financemanagementsystem.adapters.IncomeAdapter;
import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.Balances;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BalancesFragment extends Fragment {

    String BASE_URL = "https://neobisfms.herokuapp.com/";
    JsonPlaceHolderApi jsonPlaceHolderApi;
    RecyclerView recyclerView;
    String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balances, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView tv_income, tv_expense, tv_total;
        tv_income = view.findViewById(R.id.tv_income);
        tv_expense = view.findViewById(R.id.tv_expense);
        tv_total = view.findViewById(R.id.tv_total);
        recyclerView = view.findViewById(R.id.recyclerView_balances);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        token = "Token " + token;

        final Call<List<Balances>> balancesCall = jsonPlaceHolderApi.getBalances(token);
        balancesCall.enqueue(new Callback<List<Balances>>() {
            @Override
            public void onResponse(Call<List<Balances>> call, Response<List<Balances>> response) {
            List<Balances> balances = new ArrayList<>();
            balances = response.body();
            if(balances != null){
                for(Balances balance : balances) {
                    tv_income.setText(String.valueOf(balance.getTotalincome()));
                    tv_expense.setText(String.valueOf(balance.getTotalexpense()));
                    tv_total.setText(String.valueOf(balance.getTotalamount()));
                    callAccountsList();
                    //Log.v()
                }
            }
            }
            @Override
            public void onFailure(Call<List<Balances>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callAccountsList() {
        final Call<List<Accounts>> balancesCall = jsonPlaceHolderApi.getAccounts(token);
        balancesCall.enqueue(new Callback<List<Accounts>>() {
            @Override
            public void onResponse(Call<List<Accounts>> call, Response<List<Accounts>> response) {
                List<Accounts> accounts = new ArrayList<>();
                accounts = response.body();
                BalancesAdapter adapter = new BalancesAdapter(getContext(), accounts);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Accounts>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
            }
        });
    }
}