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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.adapters.ExpenseAdapter;
import com.neobis.financemanagementsystem.adapters.IncomeAdapter;
import com.neobis.financemanagementsystem.model.Expences;
import com.neobis.financemanagementsystem.model.Incomes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpenceMainFragment extends Fragment {

    private String BASE_URL = "https://neobisfms.herokuapp.com/";
    ExpenseAdapter adapter;
    RecyclerView recyclerView;
    List<Expences> expences = new ArrayList<>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView textView;
    ProgressBar progressBar;

    public ExpenceMainFragment() {
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
        return inflater.inflate(R.layout.fragment_expence_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.textView);
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
            getExpences();
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void getExpences() {
        final Call<List<Expences>> expencesCall = jsonPlaceHolderApi.getExpences();
        expencesCall.enqueue(new Callback<List<Expences>>() {
            @Override
            public void onResponse(Call<List<Expences>> call, Response<List<Expences>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                expences = response.body();
                if (recyclerView != null) {
                    adapter = new ExpenseAdapter(getContext(), expences);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Log.v("EMF", "Data:" + expences);
                }
                else textView.setVisibility(View.VISIBLE);
                    adapter.clear();
            }

            @Override
            public void onFailure(Call<List<Expences>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }
}