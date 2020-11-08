package com.neobis.financemanagementsystem.pagerAdapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.neobis.financemanagementsystem.fragments.ExpenceMainFragment;
import com.neobis.financemanagementsystem.fragments.FullMainFragment;
import com.neobis.financemanagementsystem.fragments.IncomeMainFragment;

public class MainPagerAdapter extends FragmentStateAdapter {

    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0: return new FullMainFragment();
            case 1: return new IncomeMainFragment();
            default: return new ExpenceMainFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
