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
import com.neobis.financemanagementsystem.model.Transactions;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {
    private List<Transactions> mTransactions;
    private Context mContext;

    public TransactionsAdapter(Context context, List<Transactions> items){
        this.mTransactions = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TransactionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new TransactionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsAdapter.ViewHolder holder, int position) {
        Transactions currentTransactions = mTransactions.get(position);
        if(currentTransactions.getType().equals("Expense")) {
            holder.description.setText(currentTransactions.getCategoryExpense());
            if(currentTransactions.getCounterParty() != null) {
                holder.budget.setText(String.valueOf(currentTransactions.getCounterParty()));
            } else holder.budget.setText("-");
            holder.sum.setText(String.valueOf(currentTransactions.getAmount()));
            holder.sum.setTextColor(Color.parseColor("#e60000"));
        } else if (currentTransactions.getType().equals("Income")){
            holder.description.setText(currentTransactions.getCategoryIncome());
            if(currentTransactions.getCounterParty() != null) {
                holder.budget.setText(String.valueOf(currentTransactions.getCounterParty()));
            } else holder.budget.setText("-");
            holder.sum.setText(String.valueOf(currentTransactions.getAmount()));
            holder.sum.setTextColor(Color.parseColor("#248F24"));
        } else if (currentTransactions.getType().equals("Transfer")){
            holder.description.setText("Перевод");
            holder.budget.setText(currentTransactions.getAccounts() + " -> " + currentTransactions.getSend_to());
            holder.sum.setText(String.valueOf(currentTransactions.getAmount()));
        }
        else holder.budget.setText("-");
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    public void addAll(List<Transactions> transactions) {
        this.mTransactions = transactions;
        notifyDataSetChanged();
    }

    public void clear(){
        mTransactions.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView description, budget, sum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            budget = (TextView) itemView.findViewById(R.id.budget);
            sum = (TextView) itemView.findViewById(R.id.sum);

        }
    }
}
