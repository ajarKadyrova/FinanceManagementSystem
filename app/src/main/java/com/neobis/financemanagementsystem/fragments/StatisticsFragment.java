package com.neobis.financemanagementsystem.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.neobis.financemanagementsystem.DatePickerFragment;
import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.adapters.TransactionsAdapter;
import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.CategoryOfExpense;
import com.neobis.financemanagementsystem.model.CategoryOfIncome;
import com.neobis.financemanagementsystem.model.CounterPartner;
import com.neobis.financemanagementsystem.model.FilterData;
import com.neobis.financemanagementsystem.model.Projects;
import com.neobis.financemanagementsystem.model.Transactions;
import com.neobis.financemanagementsystem.model.TransactionsWrap;

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

public class StatisticsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    EditText date_tiet_start, date_tiet_end;
    TextInputLayout date_til_start, date_til_end;
    String BASE_URL = "https://neobisfms.herokuapp.com/";
    JsonPlaceHolderApi jsonPlaceHolderApi;
    String date;
    TextView textView;
    Button action_btn;
    AutoCompleteTextView accounts_act, categories_act, contragent_act, project_act, transaction_type_act;
    private List<Accounts> accounts = new ArrayList<>();
    private List<CounterPartner> counterPartners = new ArrayList<>();
    private List<Projects> projects = new ArrayList<>();
    private List<CategoryOfIncome> categoryOfIncomes = new ArrayList<>();
    private List<CategoryOfExpense> categoryOfExpenses = new ArrayList<>();
    private long sumAcc = 0, incomeCat = 0, projectNum = 0, contrANum = 0, expense_cat;
    private HashMap<String, Long> accountsHashMap, incomesListHashMap, countPartnersHashMap, projectsHashMap, expensesListHashMap;
    int f = 0, f1 = 0, page = 0;
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    String token;
    String date_from, date_to, contrA, project, transaction, acc, inc = null, exp = null;
    Integer type = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        date_tiet_start = view.findViewById(R.id.tiet_date_start);
        date_tiet_end = view.findViewById(R.id.tiet_date_end);
        transaction_type_act = view.findViewById(R.id.act_type);
        accounts_act = view.findViewById(R.id.act_account);
        contragent_act = view.findViewById(R.id.act_contragent);
        categories_act = view.findViewById(R.id.act_categories);
        project_act = view.findViewById(R.id.act_project);
        action_btn = view.findViewById(R.id.action_btn);
        date_til_start = view.findViewById(R.id.til_date_start);
        date_til_start.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(StatisticsFragment.this, 0);
                datePicker.show(getFragmentManager(), "date picker");
            }
        });
        date_til_end = view.findViewById(R.id.til_date_end);
        date_til_end.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(StatisticsFragment.this, 0);
                datePicker.show(getFragmentManager(), "date picker");
                f = 1;
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
        token = "Token " + token;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item,
                getResources().getStringArray(R.array.transactions_type));
        transaction_type_act.setAdapter(adapter);

        final Call<List<Accounts>> callAccounts = jsonPlaceHolderApi.getAccounts(token);
        callAccounts.enqueue(new Callback<List<Accounts>>() {
            @Override
            public void onResponse(Call<List<Accounts>> call, Response<List<Accounts>> response) {
                if (!response.isSuccessful()) {
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
                for (int i = 0; i < accounts.size(); i++) {
                    accountsHashMap.put(accounts.get(i).getType(), accounts.get(i).getId());
                }
            }

            @Override
            public void onFailure(Call<List<Accounts>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });

        final Call<List<CounterPartner>> callCounterPartner = jsonPlaceHolderApi.getCounterPartner(token);
        callCounterPartner.enqueue(new Callback<List<CounterPartner>>() {
            @Override
            public void onResponse(Call<List<CounterPartner>> call, Response<List<CounterPartner>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                counterPartners = response.body();
                List counterPartner = new ArrayList<>();
                for (int i = 0; i < counterPartners.size(); i++) {
                    counterPartner.add(counterPartners.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        R.layout.dropdown_item, counterPartner);
                contragent_act.setAdapter(adapter);
                countPartnersHashMap = new HashMap<>();
                for (int i = 0; i < counterPartners.size(); i++) {
                    countPartnersHashMap.put(counterPartners.get(i).getName(), counterPartners.get(i).getId());
                }
            }

            @Override
            public void onFailure(Call<List<CounterPartner>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });

        Call<List<Projects>> callProjects = jsonPlaceHolderApi.getProjects(token);
        callProjects.enqueue(new Callback<List<Projects>>() {
            @Override
            public void onResponse(Call<List<Projects>> call, Response<List<Projects>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                projects = response.body();
                List project = new ArrayList<>();
                for (int i = 0; i < projects.size(); i++) {
                    project.add(projects.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        R.layout.dropdown_item, project);
                project_act.setAdapter(adapter);
                projectsHashMap = new HashMap<>();
                for (int i = 0; i < projects.size(); i++) {
                    projectsHashMap.put(projects.get(i).getName(), projects.get(i).getId());
                }
            }

            @Override
            public void onFailure(Call<List<Projects>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
        setCategoryOfIncome();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nestedScrollView = view.findViewById(R.id.scroll_view);
        recyclerView = view.findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        action_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                acc = accounts_act.getText().toString();
                if (accountsHashMap.containsKey(acc)) {
                    sumAcc = accountsHashMap.get(acc);
                    System.out.println(accountsHashMap);
                    Log.v("INCOMCACC", String.valueOf(sumAcc));
                }
                if (f == 0) {
                    inc = categories_act.getText().toString();
                    if (incomesListHashMap.containsKey(inc)) {
                        incomeCat = incomesListHashMap.get(inc);
                        System.out.println(incomesListHashMap);
                        Log.v("INCOMCCAT", String.valueOf(incomeCat));
                    } else incomeCat = Long.parseLong(null);
                } else if (f == 1) {
//                    inc = categories_act.getText().toString();
//                    if (expensesListHashMap.containsKey(inc)) {
//                        expense_cat = expensesListHashMap.get(inc);
//                        System.out.println(expensesListHashMap);
//                        Log.v("INCOMCCAT", String.valueOf(expense_cat));
//                    } else expense_cat = Long.parseLong(null);
                }
                project = project_act.getText().toString();
                if (projectsHashMap.containsKey(project)) {
                    projectNum = projectsHashMap.get(project);
                    System.out.println(projectsHashMap);
                    Log.v("INCOMPRO", String.valueOf(projectNum));
                } else project = null;
                contrA = contragent_act.getText().toString();
                if (countPartnersHashMap.containsKey(contrA)) {
                    contrANum = countPartnersHashMap.get(contrA);
                    System.out.println(countPartnersHashMap);
                    Log.v("INCOMCONTR", String.valueOf(contrANum));
                } else contrA = null;
                transaction = transaction_type_act.getText().toString();
                type = 1;
//                if (transaction == "Доходы") type = 1;
//                else if (transaction == "Расходы") type = 2;
//                else if (transaction == "Переводы") type = 3;
                date_from = date_tiet_start.getText().toString();
                if(date_from.isEmpty()) date_from = null;

                date_to = date_tiet_end.getText().toString();
                if(date_to.isEmpty()) date_to = null;

                FilterData filterData = new FilterData(
                        type, contrANum, projectNum,
                        incomeCat, expense_cat,
                        date_from, date_to, sumAcc
                        );
                Call<TransactionsWrap> call = jsonPlaceHolderApi.getFilterData(token,
                        type, contrA, project,
                        inc, exp,
                        date_from, date_to, acc);{
//                            call.enqueue(new Callback<TransactionsWrap>() {
//                                @Override
//                                public void onResponse(Call<TransactionsWrap> call, Response<TransactionsWrap> response) {
//                                    if(!response.isSuccessful()){
//                                        Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
//                                    }
//                                    List<Transactions> transactions = new ArrayList<>();
                                    //transactions = response.body().getTransactions();
                                    //if (transactions != null) {
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("type", type);
                                        bundle.putString("contr", contrA);
                                        bundle.putString("project", project);
                                        bundle.putString("inc", inc);
                                        bundle.putString("exp", exp);
                                        bundle.putString("from", date_from);
                                        bundle.putString("to", date_to);
                                        bundle.putString("acc", acc);
                                        Navigation.findNavController(view).navigate(R.id.action_statisticsFragment_to_statisticsFragment22, bundle);

//                                    }
//                                //}
//
//                                @Override
//                                public void onFailure(Call<TransactionsWrap> call, Throwable t) {
//
//                                }
//                            });
                }
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
        if (m < 10) {
            formattedMonth = "0" + month;
        }
        if (day < 10) {
            formattedDayOfMonth = "0" + day;
        }
        date = year + "-" + formattedMonth + "-" + formattedDayOfMonth;
        if (f == 0) {
            date_tiet_start.setText(date);
        } else if (f == 1) {
            date_tiet_end.setText(date);
        }
    }

    private void setCategoryOfIncome() {
        final Call<List<CategoryOfIncome>> callCategory = jsonPlaceHolderApi.getCategoryIncome(token);
        callCategory.enqueue(new Callback<List<CategoryOfIncome>>() {
            @Override
            public void onResponse(Call<List<CategoryOfIncome>> call, Response<List<CategoryOfIncome>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                String typeChoosen = accounts_act.getText().toString();
                if (typeChoosen == "Доходы") f = 0;
                else if (typeChoosen == "Расходы") f = 1;
                if (f1 == 0) {
                    categoryOfIncomes = response.body();
                    List category = new ArrayList<>();
                    for (int i = 0; i < categoryOfIncomes.size(); i++) {
                        category.add(categoryOfIncomes.get(i).getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                            R.layout.dropdown_item, category);
                    categories_act.setAdapter(adapter);
                    incomesListHashMap = new HashMap<>();
                    for (int i = 0; i < categoryOfIncomes.size(); i++) {
                        incomesListHashMap.put(categoryOfIncomes.get(i).getName(), categoryOfIncomes.get(i).getId());
                    }
                } else if (f == 1) setCategoryOfExpenses();
//                Log.v("NEXT LINE", "category of incomes");
//                System.out.println(incomesListHashMap);
            }

            @Override
            public void onFailure(Call<List<CategoryOfIncome>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                //textView.setText(t.getMessage());
            }
        });
    }

    private void setCategoryOfExpenses() {
        final Call<List<CategoryOfExpense>> callCategory = jsonPlaceHolderApi.getCategoryExpense(token);
        callCategory.enqueue(new Callback<List<CategoryOfExpense>>() {
            @Override
            public void onResponse(Call<List<CategoryOfExpense>> call, Response<List<CategoryOfExpense>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                categoryOfExpenses = response.body();
                List category = new ArrayList<>();
                for (int i = 0; i < categoryOfExpenses.size(); i++) {
                    category.add(categoryOfExpenses.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        R.layout.dropdown_item, category);
                categories_act.setAdapter(adapter);
                expensesListHashMap = new HashMap<>();
                for (int i = 0; i < categoryOfExpenses.size(); i++) {
                    expensesListHashMap.put(categoryOfExpenses.get(i).getName(), categoryOfExpenses.get(i).getId());
                }
            }

            @Override
            public void onFailure(Call<List<CategoryOfExpense>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });
    }
}