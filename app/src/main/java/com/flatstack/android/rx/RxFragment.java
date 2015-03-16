package com.flatstack.android.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import org.jetbrains.annotations.NotNull;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by adelnizamutdinov on 08/12/14
 */
public class RxFragment extends Fragment {
  final Subject<LifeCycleEvent, LifeCycleEvent> events = PublishSubject.create();

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FragmentArgs.inject(this);
    events.onNext(LifeCycleEvent.CREATE);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    events.onNext(LifeCycleEvent.VIEW_CREATED);
  }

  @Override public void onResume() {
    super.onResume();
    events.onNext(LifeCycleEvent.RESUME);
  }

  @Override public void onPause() {
    events.onNext(LifeCycleEvent.PAUSE);
    super.onPause();
  }

  @Override public void onDestroyView() {
    events.onNext(LifeCycleEvent.DESTROY_VIEW);
    super.onDestroyView();
  }

  @Override public void onDestroy() {
    events.onNext(LifeCycleEvent.DESTROY);
    super.onDestroy();
  }

  protected @NotNull Observable<LifeCycleEvent> pauses() {
    return events.filter(ev -> ev == LifeCycleEvent.PAUSE);
  }

  protected @NotNull Observable<LifeCycleEvent> destroyViews() {
    return events.filter(ev -> ev == LifeCycleEvent.DESTROY_VIEW);
  }

  protected RxActivity activity() {
    return (RxActivity) getActivity();
  }
}
