package com.flatstack.android.utils.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.flatstack.android.R;
import com.flatstack.android.utils.Keyboard;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable @Bind(R.id.toolbar) View toolbar;

    @NonNull public abstract UiInfo getUiInfo();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getUiInfo().getLayoutRes());
        ButterKnife.bind(this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            parseArguments(getIntent().getExtras());
        }
        if (toolbar != null) {
            setSupportActionBar((Toolbar) toolbar);
            if (getUiInfo().getTitleRes() != 0) {
                setTitle(getUiInfo().getTitleRes());
            } else if (getUiInfo().getTitle() != null) {
                setTitle(getUiInfo().getTitle());
            }
            if (getUiInfo().isHasBackButton()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }
    }

    @Override public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected void restoreState(@NonNull Bundle savedState) {
    }

    protected void parseArguments(@NonNull Bundle extras) {
        throw new IllegalStateException("should be overridden");
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        if (getUiInfo().getMenuRes() != 0) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(getUiInfo().getMenuRes(), menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home && getUiInfo().isHasBackButton()) {
            Keyboard.hide(this);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Context context() {
        return this;
    }
}
