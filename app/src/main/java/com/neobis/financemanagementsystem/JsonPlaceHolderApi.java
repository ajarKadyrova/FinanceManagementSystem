package com.neobis.financemanagementsystem;

import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.CategoryOfIncome;
import com.neobis.financemanagementsystem.model.CounterPartner;
import com.neobis.financemanagementsystem.model.Departments;
import com.neobis.financemanagementsystem.model.Expences;
import com.neobis.financemanagementsystem.model.Incomes;
import com.neobis.financemanagementsystem.model.Projects;
import com.neobis.financemanagementsystem.model.Transactions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("api/neobis")
    Call<List<Departments>> getDepartments();

    @GET("api/transactions/")
    Call<List<Transactions>> getTransactions();

    @GET("api/income/getincome/")
    Call<List<Incomes>> getIncomes();

    @GET("api/expense/getexpense/")
    Call<List<Expences>> getExpences();

    @GET("api/data/accounts/")
    Call<List<Accounts>> getAccounts();

    @GET("api/data/partner/")
    Call<List<CounterPartner>>getCounterPartner();

    @GET("api/data/projects/")
    Call<List<Projects>>getProjects();

    @GET("api/income/categoryofincome")
    Call<List<CategoryOfIncome>>getCategory();

    @POST("api/income/")
    Call<Incomes> createIncome(@Body Incomes incomes);

    @POST("api/neobis/")
    Call<Departments> createDepartment(@Body Departments department);

    @POST("api/data/accounts/")
    Call<Accounts> createAccount(@Body Accounts account);

    @POST("api/data/projects/")
    Call<Projects> createProject(@Body Projects project);

    @POST("api/data/partner/")
    Call<Projects> createСounter(@Body Projects project);
}
