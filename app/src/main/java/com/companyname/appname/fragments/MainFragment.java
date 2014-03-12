package com.companyname.appname.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyname.appname.R;
import com.companyname.appname.dagger.Dagger;
import com.companyname.appname.qualifiers.IOThreadPool;
import com.companyname.appname.qualifiers.MainThread;
import com.companyname.appname.utils.Preferences;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.observables.AndroidObservable;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
public class MainFragment extends Fragment {
    @Inject                        Preferences preferences;
    @Inject @IOThreadPool          Scheduler   ioThreadPool;
    @InjectView(R.id.default_text) TextView    defaultText;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Dagger.inject(this);
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_fragment, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getActivity() != null && getActivity().getActionBar() != null) {
            getActivity().getActionBar().setHomeButtonEnabled(false);
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
            getActivity().getActionBar().setTitle(R.string.app_name);
        }
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        Observable<String> defaultString = Observable.create((Subscriber<? super String> subscriber) -> {
            subscriber.onNext(preferences.defaultString());
            subscriber.onCompleted();
        }).subscribeOn(ioThreadPool);
        AndroidObservable.fromFragment(this, defaultString).subscribe(defaultText::setText);
    }

    @Override public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction()
                            .replace(android.R.id.content, new PrefsFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
