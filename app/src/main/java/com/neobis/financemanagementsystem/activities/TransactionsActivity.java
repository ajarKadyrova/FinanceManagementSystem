package com.neobis.financemanagementsystem.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.neobis.financemanagementsystem.R;
import com.neobis.financemanagementsystem.pagerAdapters.TransactionsPagerAdapter;

public class TransactionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        ViewPager2 viewPager = findViewById(R.id.viewPager_transactions);
        viewPager.setAdapter(new TransactionsPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout_transactions);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: {
                        tab.setText(getString(R.string.incomes));
                        break;
                    }
                    case 1: {
                        tab.setText(getString(R.string.expenses));
                        break;
                    }
                    case 2: {
                        tab.setText(getString(R.string.transfer));
                        break;
                    }
                }
            }
        });
        tabLayoutMediator.attach();
    }
}