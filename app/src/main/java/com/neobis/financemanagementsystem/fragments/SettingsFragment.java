package com.neobis.financemanagementsystem.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsFragment extends Fragment {

    private String BASE_URL = "https://neobisfms.herokuapp.com/";
    JsonPlaceHolderApi jsonPlaceHolderApi;
    NavController navController;
    String token;
    TextView newContrAgent, newAccount, newCategoryIncome, newCategoryExpense, newProject, name, email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        newContrAgent = view.findViewById(R.id.new_contrAgent_tv);
        newAccount = view.findViewById(R.id.new_account_tv);
        newCategoryIncome = view.findViewById(R.id.new_categoryIncome_tv);
        newCategoryExpense = view.findViewById(R.id.new_categoryExpense_tv);
        newProject = view.findViewById(R.id.new_project_tv);
        name = view.findViewById(R.id.name_tv);
        email = view.findViewById(R.id.email_tv);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        token ="Token " + token;
        //navController = Navigation.findNavController(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newContrAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SettingsFragmentDirections.ActionSettingsFragmentToSettingsFragment2 action =
//                        SettingsFragmentDirections.actionSettingsFragmentToSettingsFragment2();
//                action.setMessage("contragent");
//                navController.navigate(action);
                Bundle bundle = new Bundle();
                bundle.putString("key", "contragent");
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_newDataFragment, bundle);
                //navController.navigate(R.id.action_settingsFragment_to_settingsFragment2);
            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "account");
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_newDataFragment, bundle);
                //navController.navigate(R.id.action_settingsFragment_to_settingsFragment2);
            }
        });

        newCategoryIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "categoryIncome");
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_newDataFragment, bundle);
                //navController.navigate(R.id.action_settingsFragment_to_settingsFragment2);
            }
        });

        newCategoryExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "categoryExpense");
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_newDataFragment, bundle);
                //navController.navigate(R.id.action_settingsFragment_to_settingsFragment2);
            }
        });

        newProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "project");
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_newDataFragment, bundle);
                //navController.navigate(R.id.action_settingsFragment_to_settingsFragment2);
            }
        });
    }

    private void getUser(){
        Call<List<UserInfo>> call = jsonPlaceHolderApi.getUserInfo(token);
        call.enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                }
                List<UserInfo> userInfoList = response.body();
                for(UserInfo userInfo: userInfoList) {
                    name.setText(userInfo.getName());
                    email.setText(userInfo.getEmail());
                }
            }

            @Override
            public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
            }
        });
    }
}