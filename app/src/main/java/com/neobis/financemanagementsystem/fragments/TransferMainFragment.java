package com.neobis.financemanagementsystem.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.adapters.TransferAdapter;
import com.neobis.financemanagementsystem.model.Transfer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferMainFragment extends Fragment {

    private String BASE_URL = "https://neobisfms.herokuapp.com/";
    String token;
    TransferAdapter adapter;
    RecyclerView recyclerView;
    List<Transfer> transfers = new ArrayList<>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar, progressBar_bottom;
    TextView textView;
    int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transfer_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.textview_check);
        nestedScrollView = view.findViewById(R.id.scroll_view);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar_bottom = view.findViewById(R.id.progress_bar_bottom);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        token ="Token " + token;
        checkConnection();
    }

    private void checkConnection() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            getTransfers();
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    private void getTransfers() {
        final Call<List<Transfer>> incomesCall = jsonPlaceHolderApi.getTransfersList(token);
        incomesCall.enqueue(new Callback<List<Transfer>>() {
            @Override
            public void onResponse(Call<List<Transfer>> call, Response<List<Transfer>> response) {
                if (!response.isSuccessful()) {
                    //textView.setText("Code:" + response.code());
                    Log.v("Income", String.valueOf(response.code()));
                    return;
                }
                transfers = response.body();

                if (transfers != null) {
                    progressBar_bottom.setVisibility(View.GONE);
                    adapter = new TransferAdapter(getContext(), transfers);
                    recyclerView.setAdapter(adapter);
                    //textView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);

                    nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                        @Override
                        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                            if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                                page++;
                                progressBar_bottom.setTransitionVisibility(View.VISIBLE);
                                getTransfers();
                            }
                        }
                    });
                } else adapter.clear();
                if (transfers == null && progressBar.getVisibility() == View.GONE)
                    textView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Transfer>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                //textView.setText(t.getMessage());
                Log.v("Income", t.getMessage());
            }
        });
    }
}
