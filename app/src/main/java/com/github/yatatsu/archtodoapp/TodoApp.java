package com.github.yatatsu.archtodoapp;

import com.github.yatatsu.archtodoapp.di.AppInjector;
import com.github.yatatsu.archtodoapp.di.DaggerAppComponent;
import com.jakewharton.threetenabp.AndroidThreeTen;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class TodoApp extends DaggerApplication {

  @Override public void onCreate() {
    super.onCreate();

    AndroidThreeTen.init(this);
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    AppInjector.init(this);
  }

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}
