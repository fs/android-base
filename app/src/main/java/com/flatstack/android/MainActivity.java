package com.flatstack.android;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.InjectView;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.dagger.Injector;
import com.flatstack.android.fragments.MainFragmentBuilder;
import com.flatstack.android.rx.RxActivity;
import com.flatstack.android.utils.Persistence;
import com.flatstack.android.utils.StatusBar;
import dagger.Lazy;
import dagger.ObjectGraph;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MainActivity extends RxActivity implements Injector {
  @Inject Lazy<Persistence> persistence;

  @InjectView(R.id.toolbar) Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dagger.inject(this);

    setContentView(R.layout.activity_main);
    StatusBar.setup(this);
    setSupportActionBar(toolbar);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.content, new MainFragmentBuilder(R.id.content).build())
          .commit();
    }
  }

  @NotNull @Override public ObjectGraph getObjectGraph() {
    return Dagger.getObjectGraph(getApplication());
  }
}