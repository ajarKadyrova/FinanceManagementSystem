package com.neobis.financemanagementsystem.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.neobis.financemanagementsystem.DatePickerFragment;
import com.neobis.financemanagementsystem.JsonPlaceHolderApi;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.adapters.IncomeAdapter;
import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.CategoryOfIncome;
import com.neobis.financemanagementsystem.model.CounterPartner;
import com.neobis.financemanagementsystem.model.Departments;
import com.neobis.financemanagementsystem.model.Incomes;
import com.neobis.financemanagementsystem.model.PostIncome;
import com.neobis.financemanagementsystem.model.Projects;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IncomeFragment extends Fragment implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    EditText date_et, sum_et, description_et, tags_et;
    String BASE_URL = "https://neobisfms.herokuapp.com/";
    String currentDate, spinnerText;
    TextView textView;
    Button save_btn;
    Spinner spinnerAccount, spinnerCategory, spinnerContrAgent, spinnerProject;
    List<Departments> departments = new ArrayList<>();
    List<Accounts> accounts = new ArrayList<>();
    List<CounterPartner> counterPartners = new ArrayList<>();
    List<Projects> projects = new ArrayList<>();
    List<Incomes> incomesList = new ArrayList<>();
    List<CategoryOfIncome> categoryOfIncomes = new ArrayList<>();

    public IncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_income, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date_et = view.findViewById(R.id.date_et);
        sum_et = view.findViewById(R.id.sum_et);
        description_et = view.findViewById(R.id.description_et);
        tags_et = view.findViewById(R.id.tags_et);
        textView = view.findViewById(R.id.trial_schet);
        save_btn = view.findViewById(R.id.save_btn);
        spinnerAccount = view.findViewById(R.id.spinner_account);
        spinnerCategory = view.findViewById(R.id.spinner_project);
        spinnerContrAgent = view.findViewById(R.id.spinner_contragent);
        spinnerProject = view.findViewById(R.id.spinner_category);
        ImageButton imageButton = view.findViewById(R.id.date_ib);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(IncomeFragment.this, 0);
                datePicker.show(getFragmentManager(), "date picker");
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        final Call<List<Accounts>> callAccounts = jsonPlaceHolderApi.getAccounts();
        callAccounts.enqueue(new Callback<List<Accounts>>() {
            @Override
            public void onResponse(Call<List<Accounts>> call, Response<List<Accounts>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                accounts = response.body();
                List account = new ArrayList<>();
                account.add(getResources().getString(R.string.selectAccount));
                for (int i = 0; i < accounts.size(); i++) {
                    account.add(accounts.get(i).getType());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, account);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerAccount.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Accounts>> call, Throwable t) {
                Toast.makeText(getContext(), "Smth went wrong", Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });

        final Call<List<Departments>> callProjects = jsonPlaceHolderApi.getDepartments();
        callProjects.enqueue(new Callback<List<Departments>>() {
            @Override
            public void onResponse(Call<List<Departments>> call, Response<List<Departments>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                departments = response.body();
                List project = new ArrayList<>();
                project.add(getResources().getString(R.string.selectProject));
                for (int i = 0; i<departments.size(); i++) {
                    project.add(departments.get(i).getDepName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, project);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProject.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<Departments>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });

        final Call<List<CounterPartner>> callCounterPartner = jsonPlaceHolderApi.getCounterPartner();
        callCounterPartner.enqueue(new Callback<List<CounterPartner>>() {
            @Override
            public void onResponse(Call<List<CounterPartner>> call, Response<List<CounterPartner>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                counterPartners = response.body();
                List counterPartner = new ArrayList<>();
                counterPartner.add(getResources().getString(R.string.selectCounterPartner));
                for (int i = 0; i<counterPartners.size(); i++) {
                    counterPartner.add(counterPartners.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, counterPartner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerContrAgent.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<CounterPartner>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });

        final Call<List<CategoryOfIncome>> callCategory = jsonPlaceHolderApi.getCategory();
        callCategory.enqueue(new Callback<List<CategoryOfIncome>>() {
            @Override
            public void onResponse(Call<List<CategoryOfIncome>> call, Response<List<CategoryOfIncome>> response) {
                if(!response.isSuccessful()){
                    textView.setText("Code:" + response.code());
                    return;
                }
                categoryOfIncomes = response.body();
                List category = new ArrayList<>();
                category.add(getResources().getString(R.string.selectProject));
                for (int i = 0; i<categoryOfIncomes.size(); i++) {
                    category.add(categoryOfIncomes.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, category);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategory.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<CategoryOfIncome>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
            }
        });

        spinnerAccount.setOnItemSelectedListener(this);
        spinnerCategory.setOnItemSelectedListener(this);
        spinnerContrAgent.setOnItemSelectedListener(this);
        spinnerProject.setOnItemSelectedListener(this);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sum, description, tag;
                Float sum1;
                sum = sum_et.getText().toString().trim();
                description = description_et.getText().toString().trim();
                if(description.isEmpty()){
                    description = "-";
                }
                List<String> tags = Collections.singletonList(tags_et.getText().toString().trim());
                if(tags.isEmpty()) tags = Collections.singletonList("-");
                if (!currentDate.isEmpty() && !sum.isEmpty() &&
                        spinnerAccount.getSelectedItem().toString()
                                != getResources().getString(R.string.selectAccount)) {
                    sum1 = Float.parseFloat(sum);
                    PostIncome newIncome = new PostIncome(currentDate, spinnerCategory.getSelectedItem().hashCode(),
                            sum1, spinnerContrAgent.getSelectedItem().hashCode(),
                            spinnerAccount.getSelectedItem().hashCode(),
                            spinnerProject.getSelectedItem().hashCode(),
                            "Income", description, tags);
                    Call<PostIncome> callNewIncome = jsonPlaceHolderApi.createIncome(newIncome);
                    callNewIncome.enqueue(new Callback<PostIncome>() {
                        @Override
                        public void onResponse(Call<PostIncome> call, Response<PostIncome> response) {
                            if(!response.isSuccessful()){
                                textView.setText("Code:" + response.code());
                                return;
                            }
                            PostIncome incomesResponce = response.body();
                            Toast.makeText(getContext(), getResources().getString(R.string.saved_data),
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<PostIncome> call, Throwable t) {
                            Toast.makeText(getContext(), getResources().getString(R.string.mistake), Toast.LENGTH_SHORT).show();
                            textView.setText(t.getMessage());
                        }
                    });
                } else if (currentDate.isEmpty()) {
                    Toast.makeText(getContext(), getResources().getString(R.string.alertMessage_date),
                            Toast.LENGTH_LONG).show();
                } else if (sum.isEmpty()) {
                    Toast.makeText(getContext(), getResources().getString(R.string.alertMessage_sum),
                            Toast.LENGTH_LONG).show();
                } else if (spinnerAccount.getSelectedItem().toString()
                        == getResources().getString(R.string.selectAccount)) {
                    Toast.makeText(getContext(), getResources().getString(R.string.alertMessage_account),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
        date_et.setText(currentDate);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerText = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}