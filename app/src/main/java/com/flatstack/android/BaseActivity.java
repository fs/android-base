package com.flatstack.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.Dart;

import butterknife.ButterKnife;

/**
 * Created by adelnizamutdinov on 05/12/14
 */
public class BaseActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dart.inject(this);
    }

    @Override public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
}
