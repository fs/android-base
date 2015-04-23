package com.flatstack.android;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.flatstack.android.fragments.MainFragmentBuilder;
import com.flatstack.android.rx.RxActivity;
import com.flatstack.android.utils.Persistence;
import com.flatstack.android.utils.StatusBar;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import butterknife.InjectView;
import dagger.Lazy;

public class MainActivity extends RxActivity {
  @Inject Lazy<Persistence> persistence;

  @InjectView(R.id.toolbar) Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    StatusBar.setup(this);
    setSupportActionBar(toolbar);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.content, new MainFragmentBuilder(R.id.content).build())
          .commit();
    }

  }
}