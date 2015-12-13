package com.flatstack.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.flatstack.android.fragments.MainFragmentBuilder;
import com.flatstack.android.rx.RxActivity;

import butterknife.Bind;

public class MainActivity extends RxActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new MainFragmentBuilder(R.id.content).build())
                .commit();
        }

    }
}