package com.flatstack.android.rx;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.f2prateek.dart.Dart;
import com.flatstack.android.base.ModularActionBarActivity;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.dagger.Injector;
import dagger.ObjectGraph;
import org.jetbrains.annotations.NotNull;

/**
 * Created by adelnizamutdinov on 05/12/14
 */
public class RxActivity extends ModularActionBarActivity implements Injector {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dart.inject(this);
  }

  @Override public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.inject(this);
  }

  @NotNull @Override public ObjectGraph getObjectGraph() {
    return Dagger.getObjectGraph(getApplication());
  }
}
