package com.neobis.financemanagementsystem.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.adapters.TransactionsAdapter;
import com.neobis.financemanagementsystem.model.Transactions;
import com.neobis.financemanagementsystem.model.TransactionsWrap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsFragment2 extends Fragment {

    private String BASE_URL = "https://neobisfms.herokuapp.com/";
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    SharedPreferences sharedPreferences;
    String token;
    TransactionsAdapter adapter;
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    List<Transactions> transactions = new ArrayList<>();
    TextView textView;
    ProgressBar progressBar, progressBar_bottom;
    int page = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistics2, container, false);
        textView = view.findViewById(R.id.textView);
        nestedScrollView = view.findViewById(R.id.scroll_view);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar_bottom = view.findViewById(R.id.progress_bar_bottom);
        recyclerView = view.findViewById(R.id.recyclerView_statistics);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        sharedPreferences = getContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        token ="Token " + token;
        Log.v("ALLFMS", token);
        getTransactions();
        return view;
    }

    private void getTransactions() {
        String contrA, project, inc, exp, date_from, date_to,acc;
        Integer type;
        //type= getArguments().getInt("type");
        type = 1;
        contrA= getArguments().getString("contr");
        project= getArguments().getString("project");
        inc= getArguments().getString("inc");
        exp= getArguments().getString("exp");
        date_from= getArguments().getString("from");
        date_to = getArguments().getString("to");
        acc = getArguments().getString("acc");
        final Call<TransactionsWrap> departments = jsonPlaceHolderApi.getFilterData(token,
                type, contrA, project,
                inc, exp,
                date_from, date_to, acc);
        departments.enqueue(new Callback<TransactionsWrap>() {
            @Override
            public void onResponse(Call<TransactionsWrap> call, Response<TransactionsWrap> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                transactions = response.body().getTransactions();
                if (transactions != null) {
                    progressBar_bottom.setVisibility(View.GONE);
                    adapter = new TransactionsAdapter(getContext(), transactions);
                    recyclerView.setAdapter(adapter);
                    //textView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                        @Override
                        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                            if(scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                                page++;
                                progressBar_bottom.setTransitionVisibility(View.VISIBLE);
                                getTransactions();
                            }
                        }
                    });
                    Log.i("EMF", "Data:" + transactions);
                }
                else adapter.clear();
                if(transactions == null && progressBar.getVisibility() == View.GONE) textView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<TransactionsWrap> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
                Log.i("TRANSACTIONS", t.getMessage());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}