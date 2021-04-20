package com.neobis.financemanagementsystem.pagerAdapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.neobis.financemanagementsystem.fragments.ExpenceMainFragment;
import com.neobis.financemanagementsystem.fragments.FullMainFragment;
import com.neobis.financemanagementsystem.fragments.IncomeMainFragment;
import com.neobis.financemanagementsystem.fragments.TransferFragment;
import com.neobis.financemanagementsystem.fragments.TransferMainFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FullMainFragment();
            case 1:
                return new IncomeMainFragment();
            case 2:
                return new ExpenceMainFragment();
            case 3:
                return new TransferMainFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        switch (position){
            case 0:
                return "Все";
            case 1:
                return "Доход";
            case 2:
                return "Расход";
            case 3:
                return "Перевод";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
