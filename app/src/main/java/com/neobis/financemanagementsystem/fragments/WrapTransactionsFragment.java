package com.neobis.financemanagementsystem.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.pagerAdapters.MainPagerAdapter;

public class WrapTransactionsFragment extends Fragment {

    ViewPager viewPager;
    MainPagerAdapter mainPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wrap_transactions, container, false);
        viewPager = view.findViewById(R.id.viewPager_wrap);
        mainPagerAdapter = new MainPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mainPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE);
//        String token = sharedPreferences.getString("token", "");
//        Toast.makeText(getContext(), token, Toast.LENGTH_LONG).show();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

