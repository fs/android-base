package com.flatstack.android.module;

import android.support.v4.app.Fragment;
import android.view.MenuItem;
import com.flatstack.android.rx.RxActivity;
import com.flatstack.android.rx.RxModule;
import com.flatstack.android.utils.HomeAsUp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.plusonesoftware.modular.MenuCallbacks;

/**
 * Created by adelnizamutdinov on 10/12/14
 */
public class HomeAsUpModule extends RxModule implements
    MenuCallbacks.onOptionsItemSelectedCallback {
  final boolean isFragment;

  public HomeAsUpModule(@NotNull RxActivity activity, @Nullable Fragment fragment) {
    super(activity);
    HomeAsUp.enable(activity);
    if (fragment != null) {
      fragment.setHasOptionsMenu(true);
    }
    isFragment = fragment != null;
  }

  @Override public boolean onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == android.R.id.home) {
      if (isFragment) {
        activity.getSupportFragmentManager().popBackStack();
      } else {
        activity.onBackPressed();
      }
      return true;
    }
    return false;
  }
}
