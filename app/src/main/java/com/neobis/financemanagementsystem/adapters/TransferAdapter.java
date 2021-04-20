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
import com.neobis.financemanagementsystem.model.Transactions;
import com.neobis.financemanagementsystem.model.Transfer;

import java.util.List;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.ViewHolder> {

    private List<Transfer> mTransfer;
    private Context mContext;

    public TransferAdapter(Context context, List<Transfer> items){
        this.mTransfer = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TransferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new TransferAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.ViewHolder holder, int position) {
        Transfer currentTransfer = mTransfer.get(position);
        holder.description.setText("Перевод");
        holder.budget.setText(currentTransfer.getAccounts() + " -> " + currentTransfer.getSend_to());
        holder.sum.setText(String.valueOf(currentTransfer.getAmount()));
    }

    @Override
    public int getItemCount() {
        return mTransfer.size();
    }

    public void clear(){
        mTransfer.clear();
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView description, budget, sum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            budget = (TextView) itemView.findViewById(R.id.budget);
            sum = (TextView) itemView.findViewById(R.id.sum);
        }
    }
}
