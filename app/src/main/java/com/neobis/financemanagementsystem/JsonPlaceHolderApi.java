package com.neobis.financemanagementsystem;

import com.neobis.financemanagementsystem.model.Accounts;
import com.neobis.financemanagementsystem.model.AuthToken;
import com.neobis.financemanagementsystem.model.Balances;
import com.neobis.financemanagementsystem.model.CategoryOfExpense;
import com.neobis.financemanagementsystem.model.CategoryOfIncome;
import com.neobis.financemanagementsystem.model.CounterPartner;
import com.neobis.financemanagementsystem.model.EmailResetPassword;
import com.neobis.financemanagementsystem.model.ExpenseWrap;
import com.neobis.financemanagementsystem.model.IncomeWrap;
import com.neobis.financemanagementsystem.model.PostExpense;
import com.neobis.financemanagementsystem.model.PostIncome;
import com.neobis.financemanagementsystem.model.PostTransfer;
import com.neobis.financemanagementsystem.model.Projects;
import com.neobis.financemanagementsystem.model.TransactionsWrap;
import com.neobis.financemanagementsystem.model.Transfer;
import com.neobis.financemanagementsystem.model.Login;
import com.neobis.financemanagementsystem.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("api/transactions/")
    Call<TransactionsWrap> getTransactionsList(@Header("Authorization") String authToken,
            @Query("count") int page
    );

    @GET("api/income/getincome/")
    Call<IncomeWrap> getIncomesList(@Header("Authorization") String authToken,
            @Query("count") int page
    );

    @GET("api/expense/getexpense/")
    Call<ExpenseWrap> getExpensesList(@Header("Authorization") String authToken,
            @Query("count") int page
    );

    @GET("api/transfer/")
    Call<List<Transfer>> getTransfersList(@Header("Authorization") String authToken);

    @GET("api/data/accounts/")
    Call<List<Accounts>> getAccounts(@Header("Authorization") String authToken);

    @GET("api/data/partner/")
    Call<List<CounterPartner>>getCounterPartner(@Header("Authorization") String authToken);

    @GET("api/data/projects/")
    Call<List<Projects>>getProjects(@Header("Authorization") String authToken);

    @GET("api/income/categoryofincome")
    Call<List<CategoryOfIncome>>getCategoryIncome(@Header("Authorization") String authToken);

    @GET("api/expense/categoryofexpense")
    Call<List<CategoryOfExpense>>getCategoryExpense(@Header("Authorization") String authToken);

    @GET("api/gettotal/")
    Call<List<Balances>>getBalances(@Header("Authorization") String token);

    @GET("api/user/getuser")
    Call<List<UserInfo>>getUserInfo(@Header("Authorization") String token);

    @GET("api/transactions/")
    Call<TransactionsWrap> getFilteredData(
            @Header("Authorization") String token,
            @Query("type") long type,
            @Query("counterparty") long counterparty,
            @Query("projects") long project,
            @Query("categoryincome") long categoryincome,
            @Query("categoryexpense") long categoryexpense,
            @Query("start_date") String start_date,
            @Query("end_date") String end_date,
            @Query("account") long account
    );

    @GET("api/transactions/")
    Call<TransactionsWrap> getFilterData(
            @Header("Authorization") String token,
            @Query("type") long type,
            @Query("counterparty") String counterparty,
            @Query("projects") String project,
            @Query("categoryincome") String categoryincome,
            @Query("categoryexpense") String categoryexpense,
            @Query("start_date") String start_date,
            @Query("end_date") String end_date,
            @Query("account") String account
    );

    @POST("api/user/login/")
    Call<AuthToken> authUser(@Body Login login);

    @POST("api/income/")
    Call<PostIncome> createIncome(@Header("Authorization") String authToken, @Body PostIncome income);

    @POST("api/expense/")
    Call<PostExpense> createExpense(@Header("Authorization") String authToken, @Body PostExpense expense);

    @POST("api/transfer/")
    Call<PostTransfer> createTransfer(@Header("Authorization") String authToken,@Body PostTransfer transfer);

    @POST("api/data/accounts/")
    Call<Accounts> createAccount(@Header("Authorization") String authToken, @Body Accounts account);

    @POST("api/data/projects/")
    Call<Projects> createProject(@Header("Authorization") String authToken, @Body Projects project);

    @POST("api/data/partner/")
    Call<Projects> createСounter(@Header("Authorization") String authToken, @Body Projects project);

    @POST("api/income/categoryofincome/")
    Call<CategoryOfIncome>createCategoryIncome(@Header("Authorization") String authToken, @Body CategoryOfIncome categoryOfIncome);

    @POST("api/income/categoryofincome/")
    Call<CategoryOfExpense>createCategoryExpense(@Header("Authorization") String authToken, @Body CategoryOfExpense categoryOfExpense);

    @POST("api/user/reset-password/")
    Call<EmailResetPassword> resetPassword(@Header("Authorization") String authToken,
                                           @Body EmailResetPassword email);
}
