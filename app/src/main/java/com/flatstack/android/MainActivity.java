package com.flatstack.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.dagger.Injector;
import com.flatstack.android.fragments.MainFragment;
import com.flatstack.android.fragments.PrefsFragment;
import com.flatstack.android.utils.Persistence;
import dagger.Lazy;
import dagger.ObjectGraph;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MainActivity extends Activity implements Injector {
  @Inject Lazy<Persistence> persistence;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dagger.inject(this);
    FragmentManager fm = getFragmentManager();
    if (fm.findFragmentById(android.R.id.content) == null) {
      fm.beginTransaction()
          .add(android.R.id.content, new MainFragment())
          .commit();
    }
  }

  @Override public boolean onCreateOptionsMenu(@NotNull Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(@NotNull MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        getFragmentManager().popBackStack();
        return true;

      case R.id.action_settings:
        getFragmentManager().beginTransaction()
            .replace(android.R.id.content, new PrefsFragment())
            .addToBackStack(null)
            .commit();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @NotNull @Override public ObjectGraph getObjectGraph() {
    return Dagger.getObjectGraph(getApplication());
  }
}