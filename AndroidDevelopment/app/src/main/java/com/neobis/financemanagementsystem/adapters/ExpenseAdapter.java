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
import com.neobis.financemanagementsystem.model.Expences;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expences> mExpences;
    private Context mContext;

    public ExpenseAdapter(Context context, List<Expences> items){
        this.mContext = context;
        this.mExpences = items;
    }

    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new ExpenseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {
        Expences currentExpence = mExpences.get(position);
        holder.description.setText(currentExpence.getCategoryExpence());
        if(currentExpence.getCounterparty() !=null) {
            holder.budget.setText(String.valueOf(currentExpence.getCounterparty()));
        } else holder.budget.setText("-");
        holder.sum.setText(String.valueOf(currentExpence.getAmount()));
        holder.sum.setTextColor(Color.parseColor("#e60000"));
    }

    @Override
    public int getItemCount() {
        return mExpences.size();
    }

    public void clear() {
        mExpences.clear();
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
