package com.neobis.financemanagementsystem.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.adapters.IncomeAdapter;
import com.neobis.financemanagementsystem.adapters.TransactionsAdapter;
import com.neobis.financemanagementsystem.model.Departments;
import com.neobis.financemanagementsystem.model.Incomes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IncomeMainFragment extends Fragment {

    private String BASE_URL = "https://neobisfms.herokuapp.com/";
    IncomeAdapter adapter;
    RecyclerView recyclerView;
    List<Incomes> incomes = new ArrayList<>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    ProgressBar progressBar;
    TextView textView;

    public IncomeMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.textview_check);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        checkConnection();
    }

    private void checkConnection() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getIncomes();
        } else {
            progressBar.setVisibility(View.GONE);
        }
        
    }

    private void getIncomes() {
        final Call<List<Incomes>> departments = jsonPlaceHolderApi.getIncomes();
        departments.enqueue(new Callback<List<Incomes>>() {
            @Override
            public void onResponse(Call<List<Incomes>> call, Response<List<Incomes>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                incomes = response.body();
//                for(Incomes department: incomes){
//                    String content ="";
//                    content+= "\n" + department.getDate() + "\n";
//                    content+="Dep" + department.getComment()+ "\n";
//                    content+= department.getCategoryincome()+ "\n";
//                    content+=department.getAmount()+ "\n";
//                    textView.append(content);
//                }
                if (incomes != null) {
                    adapter = new IncomeAdapter(getContext(), incomes);
                    recyclerView.setAdapter(adapter);
                    textView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }
                else adapter.clear();
                if(incomes == null && progressBar.getVisibility() == View.GONE) textView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Incomes>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }
}