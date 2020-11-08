package com.neobis.financemanagementsystem.pagerAdapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.neobis.financemanagementsystem.fragments.ExpenceFragment;
import com.neobis.financemanagementsystem.fragments.IncomeFragment;
import com.neobis.financemanagementsystem.fragments.TransferFragment;

public class TransactionsPagerAdapter extends FragmentStateAdapter {

    public TransactionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0: return new IncomeFragment();
            case 1: return new ExpenceFragment();
            default: return new TransferFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
