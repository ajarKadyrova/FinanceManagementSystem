package com.neobis.financemanagementsystem.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.neobis.financemanagementsystem.DatePickerFragment;
import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.CategoryOfExpense;
import com.neobis.financemanagementsystem.model.CounterPartner;
import com.neobis.financemanagementsystem.model.PostExpense;
import com.neobis.financemanagementsystem.model.Projects;

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

public class ExpenceFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    EditText date_tiet, sum_tiet, description_tiet, tags_tiet;
    String BASE_URL = "https://neobisfms.herokuapp.com/";
    JsonPlaceHolderApi jsonPlaceHolderApi;
    String date;
    TextView textView;
    Button save_btn;
    AutoCompleteTextView accounts_act, categories_act, contragent_act, project_act;
    TextInputLayout date_til;
    private List<Accounts> accounts = new ArrayList<>();
    private List<CounterPartner> counterPartners = new ArrayList<>();
    private List<Projects> projects = new ArrayList<>();
    private List<CategoryOfExpense> categoryOfExpenses = new ArrayList<>();
    private long sumAcc = 0, expenseCat = 0, projectNum = 0, contrANum = 0;
    private HashMap<String, Long> accountsHashMap, expensesListHashMap, countPartnersHashMap, projectsHashMap;
    String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_expence, container, false);
        date_tiet = view.findViewById(R.id.date_tiet);
        save_btn = view.findViewById(R.id.save_btn);
        accounts_act = view.findViewById(R.id.act_accounts);
        categories_act = view.findViewById(R.id.act_categories);
        contragent_act = view.findViewById(R.id.act_contragents);
        project_act = view.findViewById(R.id.act_project_expense);
        sum_tiet = view.findViewById(R.id.sum_tiet);
        description_tiet = view.findViewById(R.id.description_tiet);
        //tags_tiet = view.findViewById(R.id.tags_tiet);
        date_til = view.findViewById(R.id.til_date);
        date_til.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(ExpenceFragment.this, 0);
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
                accounts_act.setAdapter(adapter);
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

        final Call<List<CategoryOfExpense>> callCategory = jsonPlaceHolderApi.getCategoryExpense(token);
        callCategory.enqueue(new Callback<List<CategoryOfExpense>>() {
            @Override
            public void onResponse(Call<List<CategoryOfExpense>> call, Response<List<CategoryOfExpense>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                categoryOfExpenses = response.body();
                List category = new ArrayList<>();
                for (int i = 0; i<categoryOfExpenses.size(); i++) {
                    category.add(categoryOfExpenses.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        R.layout.dropdown_item, category);
                categories_act.setAdapter(adapter);
                expensesListHashMap = new HashMap<>();
                for(int i = 0; i < categoryOfExpenses.size(); i++){
                    expensesListHashMap.put(categoryOfExpenses.get(i).getName(), categoryOfExpenses.get(i).getId());
                }
            }
            @Override
            public void onFailure(Call<List<CategoryOfExpense>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });

        final Call<List<CounterPartner>> callCounterPartner = jsonPlaceHolderApi.getCounterPartner(token);
        callCounterPartner.enqueue(new Callback<List<CounterPartner>>() {
            @Override
            public void onResponse(Call<List<CounterPartner>> call, Response<List<CounterPartner>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                counterPartners = response.body();
                List counterPartner = new ArrayList<>();
                for (int i = 0; i<counterPartners.size(); i++) {
                    counterPartner.add(counterPartners.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        R.layout.dropdown_item, counterPartner);
                contragent_act.setAdapter(adapter);
                countPartnersHashMap = new HashMap<>();
                for(int i = 0; i < counterPartners.size(); i++){
                    countPartnersHashMap.put(counterPartners.get(i).getName(), counterPartners.get(i).getId());
                }
            }
            @Override
            public void onFailure(Call<List<CounterPartner>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });

        final Call<List<Projects>> callProjects = jsonPlaceHolderApi.getProjects(token);
        callProjects.enqueue(new Callback<List<Projects>>() {
            @Override
            public void onResponse(Call<List<Projects>> call, Response<List<Projects>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                projects = response.body();
                List project = new ArrayList<>();
                for (int i = 0; i<projects.size(); i++) {
                    project.add(projects.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        R.layout.dropdown_item, project);
                project_act.setAdapter(adapter);
                projectsHashMap = new HashMap<>();
                for(int i = 0; i < projects.size(); i++){
                    projectsHashMap.put(projects.get(i).getName(), projects.get(i).getId());
                }
            }
            @Override
            public void onFailure(Call<List<Projects>> call, Throwable t) {
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
                String date, acc, category, sum;
                date = date_tiet.getText().toString();
                acc = accounts_act.getText().toString();
                category = categories_act.getText().toString();
                sum = sum_tiet.getText().toString();
                if(date.isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.alertMessage_date), Toast.LENGTH_SHORT).show();
                }
                else if(acc.isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.alertMessage_account), Toast.LENGTH_SHORT).show();
                }
                else if(category.isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.alertMessage_categry), Toast.LENGTH_SHORT).show();
                }
                else if(sum.isEmpty()){
                    Toast.makeText(getContext(), getString(R.string.alertMessage_sum), Toast.LENGTH_SHORT).show();
                }
                if(accountsHashMap.containsKey(acc)) {
                    sumAcc = accountsHashMap.get(acc);
                    System.out.println(accountsHashMap);
                    Log.v("EXPENSEACC", String.valueOf(sumAcc));
                }
                if(expensesListHashMap.containsKey(category)) {
                    expenseCat = expensesListHashMap.get(category);
                    System.out.println(expensesListHashMap);
                    Log.v("EXPENSECAT", String.valueOf(expenseCat));
                }
                String contrA, project, description;
                project = project_act.getText().toString();
                if(projectsHashMap.containsKey(project)){
                    projectNum = projectsHashMap.get(project);
                    System.out.println(projectsHashMap);
                    Log.v("EXPENSEPRO", String.valueOf(projectNum));
                }
                contrA = contragent_act.getText().toString();
                if(countPartnersHashMap.containsKey(contrA)){
                    contrANum = countPartnersHashMap.get(contrA);
                    System.out.println(countPartnersHashMap);
                    Log.v("EXPENSECONTR", String.valueOf(contrANum));
                }
                description = description_tiet.getText().toString().trim();
                if(description.isEmpty()){
                    description = " ";
                }
//                List<String> tags = Collections.singletonList(tags_tiet.getText().toString().trim());
//                if(tags.isEmpty()) tags = Collections.singletonList("");
                double sum1 = Double.parseDouble(sum);
                PostExpense postExpense = new PostExpense(date, expenseCat,
                        sum1, contrANum,
                        sumAcc,
                        projectNum,
                        "2", description);
                Log.v("EXPENSEEE", String.valueOf("Date: "+postExpense.getDate() +" "
                        +" category: "+ postExpense.getCategoryexpense() + " " +
                        " amount: "+ postExpense.getAmount() +" " +  " counterparty: "
                        + postExpense.getCounterparty() + " " + " account: "+postExpense.getAccounts() + " "
                        + " project: "+postExpense.getProjects()) + "2" +
                        " " + " description: "+postExpense.getComment());
                Call<PostExpense> callNewExpense = jsonPlaceHolderApi.createExpense(token, postExpense);
                callNewExpense.enqueue(new Callback<PostExpense>() {
                    @Override
                    public void onResponse(Call<PostExpense> call, Response<PostExpense> response) {
                        if(!response.isSuccessful()){
                            //textView.setText("Code:" + response.code());
                            Log.v("EXPENSEEE", String.valueOf(response.code()));
                            return;
                        }
                        PostExpense expensesResponce = response.body();
                        Toast.makeText(getContext(), getResources().getString(R.string.saved_data),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostExpense> call, Throwable t) {
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