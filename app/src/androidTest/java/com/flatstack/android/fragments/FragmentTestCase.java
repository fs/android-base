package com.flatstack.android.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import com.flatstack.android.rx.RxActivity;

/**
 * Created by adelnizamutdinov on 10/12/14
 */
public abstract class FragmentTestCase<T extends Fragment> extends
    ActivityInstrumentationTestCase2<RxActivity> {
  protected T fragment;

  public FragmentTestCase() { super(RxActivity.class); }

  protected abstract T create();

  @Override protected void setUp() throws Exception {
    super.setUp();
    final FragmentManager fm = getActivity().getSupportFragmentManager();
    fm.beginTransaction()
        .add(android.R.id.content, create())
        .commit();
    getInstrumentation().runOnMainSync(fm::executePendingTransactions);
    fragment = (T) fm.findFragmentById(android.R.id.content);
  }

  protected void executePendingTransactions() {
    final FragmentManager fm = getActivity().getSupportFragmentManager();
    getInstrumentation().runOnMainSync(fm::executePendingTransactions);
  }
}
