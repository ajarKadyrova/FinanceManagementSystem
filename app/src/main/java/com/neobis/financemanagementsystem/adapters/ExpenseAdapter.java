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
import com.neobis.financemanagementsystem.model.Expenses;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expenses> mExpenses;
    private Context mContext;

    public ExpenseAdapter(Context context, List<Expenses> items){
        this.mContext = context;
        this.mExpenses = items;
    }

    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new ExpenseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {
        Expenses currentExpense = mExpenses.get(position);
        if(currentExpense.getCategoryExpence() == "No category" || currentExpense.getCategoryExpence() == null){
            holder.description.setText("Без категории");
        }else holder.description.setText(currentExpense.getCategoryExpence());
        if(currentExpense.getCounterparty() !=null) {
            holder.budget.setText(String.valueOf(currentExpense.getCounterparty()));
        } else holder.budget.setText("-");
        holder.sum.setText(String.valueOf(currentExpense.getAmount()));
        holder.sum.setTextColor(Color.parseColor("#e60000"));
    }

    @Override
    public int getItemCount() {
        return mExpenses.size();
    }

    public void clear() {
        mExpenses.clear();
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
