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
import com.neobis.financemanagementsystem.model.Incomes;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

    private List<Incomes> mIncomes;
    private Context mContext;

    public IncomeAdapter(Context context, List<Incomes> items){
        this.mIncomes = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new IncomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.ViewHolder holder, int position) {
        Incomes currentIncome = mIncomes.get(position);
        if(currentIncome.getCategoryIncome() == "No category" || currentIncome.getCategoryIncome() == null){
            holder.description.setText("Без категории");
        }else holder.description.setText(currentIncome.getCategoryIncome());
        if(currentIncome.getCounterparty() != null) {
            holder.budget.setText(String.valueOf(currentIncome.getCounterparty()));
        } else holder.budget.setText("-");
        holder.sum.setText(String.valueOf(currentIncome.getAmount()));
        holder.sum.setTextColor(Color.parseColor("#248F24"));
    }

    @Override
    public int getItemCount() {
        return mIncomes.size();
    }

    public void clear() {
        mIncomes.clear();
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
