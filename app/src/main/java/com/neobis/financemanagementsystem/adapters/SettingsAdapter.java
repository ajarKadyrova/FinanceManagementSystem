package com.neobis.financemanagementsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.model.CounterPartner;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    private List<CounterPartner> mCounterPartner;
    Context mContext;

    public SettingsAdapter(Context context, List<CounterPartner> items){
        this.mContext = context;
        this.mCounterPartner = items;
    }
    @NonNull
    @Override
    public SettingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_settings, parent, false);
        return new SettingsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsAdapter.ViewHolder holder, int position) {
        CounterPartner currentCounterPartner = mCounterPartner.get(position);
        holder.category.setText(currentCounterPartner.getName());
    }

    @Override
    public int getItemCount() {
        return mCounterPartner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.name_of_category);
        }
    }
}
