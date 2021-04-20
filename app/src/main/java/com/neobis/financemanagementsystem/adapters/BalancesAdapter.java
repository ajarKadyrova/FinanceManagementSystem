package com.neobis.financemanagementsystem.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.model.Accounts;

import java.util.List;

public class BalancesAdapter extends RecyclerView.Adapter<BalancesAdapter.ViewHolder> {

    private List<Accounts> mAccounts;
    private Context mContext;

    public BalancesAdapter(Context context, List<Accounts> items){
        this.mAccounts = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public BalancesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_balances, parent, false);
        return new BalancesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BalancesAdapter.ViewHolder holder, int position) {
        Accounts currentAcc = mAccounts.get(position);
        holder.account.setText(currentAcc.getType());
        holder.balance.setText(String.valueOf(currentAcc.getAmount()));
    }

    @Override
    public int getItemCount() {
        return mAccounts.size();
    }

    public void clear() {
        mAccounts.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView balance, account;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            account = (TextView) itemView.findViewById(R.id.account);
            balance = (TextView) itemView.findViewById(R.id.balance);

        }
    }
}
