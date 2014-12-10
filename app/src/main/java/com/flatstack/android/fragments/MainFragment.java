package com.flatstack.android.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.flatstack.android.R;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.rx.RxFragment;
import com.flatstack.android.utils.DatabaseHelper;
import com.flatstack.android.utils.HomeAsUp;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.squareup.picasso.Picasso;
import dagger.Lazy;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class MainFragment extends RxFragment {
  @Inject @NotNull Lazy<Picasso>        picasso; // application scope
  @Inject @NotNull Lazy<DatabaseHelper> databaseHelper; //activity scope

  @Arg @IdRes int container;

  @Override public View onCreateView(LayoutInflater inflater,
                                     ViewGroup container,
                                     Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Dagger.inject(this);

    activity().setTitle(R.string.app_name);
    setHasOptionsMenu(true);
    HomeAsUp.disable(activity());
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_main, menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        getFragmentManager().beginTransaction()
            .replace(container, new PrefsFragment())
            .addToBackStack(null)
            .commit();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
