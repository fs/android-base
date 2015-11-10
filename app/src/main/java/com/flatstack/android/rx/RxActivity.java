package com.flatstack.android.rx;

import android.os.Bundle;

import com.f2prateek.dart.Dart;
import com.flatstack.android.base.ModularActionBarActivity;

import butterknife.ButterKnife;

/**
 * Created by adelnizamutdinov on 05/12/14
 */
public class RxActivity extends ModularActionBarActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dart.inject(this);
  }

  @Override public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.bind(this);
  }
}
