package com.neobis.financemanagementsystem.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.neobis.financemanagementsystem.DatePickerFragment;
import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.adapters.TransferAdapter;
import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.PostTransfer;
import com.neobis.financemanagementsystem.model.Transfer;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    EditText date_tiet, sum_tiet, description_tiet;
    String BASE_URL = "https://neobisfms.herokuapp.com/";
    JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView textView;
    Button save_btn;
    String date;
    AutoCompleteTextView act_accounts_from, act_accounts_to;
    TextInputLayout date_til;
    List<Accounts> accounts = new ArrayList<>();
    private HashMap<String, Long> accountsHashMap;
    private long sumAcc, sumAcc1;
String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer, container, false);

        date_tiet = view.findViewById(R.id.date_tiet);
        save_btn = view.findViewById(R.id.save_btn);
        act_accounts_from = view.findViewById(R.id.act_accounts_from);
        act_accounts_to = view.findViewById(R.id.act_accounts_to);
        sum_tiet = view.findViewById(R.id.sum_tiet);
        description_tiet = view.findViewById(R.id.description_tiet);
        date_til = view.findViewById(R.id.til_date);
        date_til = view.findViewById(R.id.til_date);
        date_til.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(TransferFragment.this, 0);
                datePicker.show(getFragmentManager(), "date picker");
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        token ="Token " + token;

        final Call<List<Accounts>> callAccounts = jsonPlaceHolderApi.getAccounts(token);
        callAccounts.enqueue(new Callback<List<Accounts>>() {
            @Override
            public void onResponse(Call<List<Accounts>> call, Response<List<Accounts>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                accounts = response.body();
                List account = new ArrayList<>();
                for (int i = 0; i < accounts.size(); i++) {
                    account.add(accounts.get(i).getType());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, account);
                act_accounts_from.setAdapter(adapter);
                act_accounts_to.setAdapter(adapter);
                accountsHashMap = new HashMap<>();
                for(int i = 0; i < accounts.size(); i++){
                    accountsHashMap.put(accounts.get(i).getType(), accounts.get(i).getId());
                }
            }
            @Override
            public void onFailure(Call<List<Accounts>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date, acc_from, acc_to, sum;
                date = date_tiet.getText().toString();
                acc_from = act_accounts_from.getText().toString();
                acc_to = act_accounts_to.getText().toString();
                sum = sum_tiet.getText().toString();
                if(date.isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.alertMessage_date), Toast.LENGTH_SHORT).show();
                }
                else if(acc_from.isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.alertMessage_account), Toast.LENGTH_SHORT).show();
                }
                else if(acc_to.isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.alertMessage_account), Toast.LENGTH_SHORT).show();
                }
                else if(sum.isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.alertMessage_sum), Toast.LENGTH_SHORT).show();
                }
                if(accountsHashMap.containsKey(acc_from)) {
                    sumAcc = accountsHashMap.get(acc_from);
                    System.out.println(accountsHashMap);
                    Log.v("EXPENSEACC", String.valueOf(sumAcc));
                }
                if(accountsHashMap.containsKey(acc_to)) {
                    sumAcc1 = accountsHashMap.get(acc_to);
                    System.out.println(accountsHashMap);
                    Log.v("EXPENSECAT", String.valueOf(sumAcc1));
                }
                if(sumAcc == sumAcc1){
                    Toast.makeText(getContext(), getResources().getString(R.string.same_accounts), Toast.LENGTH_LONG).show();
                }
                String description;
                description = description_tiet.getText().toString().trim();
                if(description.isEmpty()){
                    description = " ";
                }
//                List<String> tags = Collections.singletonList(tags_tiet.getText().toString().trim());
//                if(tags.isEmpty()) tags = Collections.singletonList("");
                double sum1 = Double.parseDouble(sum);
                PostTransfer postTransfer = new PostTransfer(date, sumAcc,
                        sum1, sumAcc1,
                        "3", description);
                Log.v("EXPENSEEE", String.valueOf("Date: "+postTransfer.getDate() +" "
                        +" acc1: "+ postTransfer.getAccounts() + " " +
                        " acc2: "+ postTransfer.getSend_to() +" " +  " amount: "
                        + postTransfer.getAmount() + " " + "3" + " description:" + postTransfer.getComment()));
                Call<PostTransfer> callNewTransfer = jsonPlaceHolderApi.createTransfer(token ,postTransfer);
                callNewTransfer.enqueue(new Callback<PostTransfer>() {
                    @Override
                    public void onResponse(Call<PostTransfer> call, Response<PostTransfer> response) {
                        if(!response.isSuccessful()){
                            //textView.setText("Code:" + response.code());
                            Log.v("EXPENSEEE", String.valueOf(response.code()));
                            return;
                        }
                        PostTransfer transferResponce = response.body();
                        Toast.makeText(getContext(), getResources().getString(R.string.saved_data),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostTransfer> call, Throwable t) {
                        Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                        textView.setText(t.getMessage());
                        Log.v("EXPENSEEE", t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String currentDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
        int m = month + 1;
        String formattedMonth = "" + m;
        String formattedDayOfMonth = "" + day;
        if(m < 10){
            formattedMonth = "0" + month;
        }
        if(day < 10){
            formattedDayOfMonth = "0" + day;
        }
        date = year + "-" + formattedMonth + "-" + formattedDayOfMonth;
        date_tiet.setText(date);
    }
}